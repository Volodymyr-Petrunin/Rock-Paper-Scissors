package org.game.rps.controller;

import org.game.rps.domain.Player;
import org.game.rps.domain.Statistic;
import org.game.rps.domain.dto.RequestPlayerDto;
import org.game.rps.security.WebSecurityConfiguration;
import org.game.rps.service.PlayerService;
import org.game.rps.service.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
@Import(WebSecurityConfiguration.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;

    private final String requestMapping = "/player";

    @Test
    @WithAnonymousUser
    void testRegisterPlayer_ShouldCallPlayerServiceAndRedirectToLogin() throws Exception {
        RequestPlayerDto dto = new RequestPlayerDto();
        dto.setName("Player");
        dto.setPassword("123");

        String urlTemplate = "/reg";
        String attributeName = "dto";
        String redirectUrl = "/login";

        mockMvc.perform(post(requestMapping + urlTemplate)
                        .flashAttr(attributeName, dto)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(redirectUrl));

        verify(playerService, times(1)).createPlayer(any());
    }

    @Test
    void testCheckStatistic_ShouldAddStatisticToModelAndReturnView() throws Exception {
        Statistic statistic = new Statistic(1L, 10, 5, 3, 2);
        UserDetailsImpl userDetails = new UserDetailsImpl(new Player(null, "Player", "123", statistic));
        String urlTemplate = "/statistic";
        String viewName = "my-statistic";
        String attribute = "statistic";

        mockMvc.perform(get(requestMapping + urlTemplate)
                        .with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName))
                .andExpect(model().attributeExists(attribute))
                .andExpect(model().attribute(attribute, statistic));
    }
}