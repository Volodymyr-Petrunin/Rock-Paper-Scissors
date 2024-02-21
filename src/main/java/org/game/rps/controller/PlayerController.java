package org.game.rps.controller;

import org.game.rps.domain.Statistic;
import org.game.rps.domain.dto.RequestPlayerDto;
import org.game.rps.service.PlayerService;
import org.game.rps.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/reg")
    public String registerPlayer(){
        return "register-player";
    }

    @PostMapping("/reg")
    public String registerPlayer(@ModelAttribute RequestPlayerDto dto) {
        playerService.createPlayer(dto);
        return "redirect:/login";
    }

    @GetMapping("/statistic")
    public String checkStatistic(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        Statistic statistic = userDetails.getPlayer().getStatistic();

        model.addAttribute("statistic", statistic);

        return "my-statistic";
    }
}
