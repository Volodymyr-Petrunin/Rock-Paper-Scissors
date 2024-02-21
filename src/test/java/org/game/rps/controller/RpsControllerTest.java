package org.game.rps.controller;

import org.game.rps.domain.Player;
import org.game.rps.domain.Result;
import org.game.rps.domain.Selection;
import org.game.rps.domain.Statistic;
import org.game.rps.domain.dto.ResponsePlayerDto;
import org.game.rps.security.WebSecurityConfiguration;
import org.game.rps.service.GameService;
import org.game.rps.service.StatisticServices;
import org.game.rps.service.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RpsController.class)
@Import(WebSecurityConfiguration.class)
class RpsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;
    @MockBean
    private StatisticServices statisticServices;

    private final String requestMapping = "/games";

    @Test
    void testSelectionDone_ShouldAddAttributesToModelAndReturnView() throws Exception {
        Statistic statistic = new Statistic(1L, 10, 5, 3, 2);
        UserDetailsImpl userDetails = new UserDetailsImpl(new Player(null, "Player", "123", statistic));

        ResponsePlayerDto responsePlayerDto = new ResponsePlayerDto(Selection.ROCK, Selection.PAPER, Result.LOSE);
        when(gameService.getWinner(Selection.ROCK)).thenReturn(responsePlayerDto);

        String urlTemplate = "/rock-paper-scissors-result";
        String flashAttr = "selection";
        String viewName = "rock-paper-scissors-result";
        String attribute = "responsePlayerDto";

        mockMvc.perform(get(requestMapping + urlTemplate)
                        .flashAttr(flashAttr, responsePlayerDto.getPlayerPick())
                        .with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName))
                .andExpect(model().attributeExists(attribute))
                .andExpect(model().attribute(attribute, responsePlayerDto));

        verify(gameService, times(1)).getWinner(Selection.ROCK);
        verify(statisticServices, times(1)).updateStatistic(userDetails, responsePlayerDto.getResult());
    }
}