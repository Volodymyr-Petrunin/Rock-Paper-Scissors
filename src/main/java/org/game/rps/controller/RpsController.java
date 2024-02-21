package org.game.rps.controller;

import org.game.rps.domain.Selection;
import org.game.rps.domain.dto.ResponsePlayerDto;
import org.game.rps.service.GameService;
import org.game.rps.service.StatisticServices;
import org.game.rps.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The RpsController class represents a controller for the "Rock, Paper, Scissors" game.
 * Rps - "Rock, Paper, Scissors"
 */

@Controller
@RequestMapping("/games")
public class RpsController {

    private final GameService gameService;
    private final StatisticServices statisticServices;

    @Autowired
    public RpsController(GameService gameService, StatisticServices statisticServices) {
        this.gameService = gameService;
        this.statisticServices = statisticServices;
    }

    @GetMapping("/rock-paper-scissors")
    public String selectionRps(Model model){
        model.addAttribute("selection", Selection.values());
        return "rock-paper-scissors";
    }

    @GetMapping("/rock-paper-scissors-result")
    public String selectionDone(@ModelAttribute Selection playerPick, Model model,
                                @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        ResponsePlayerDto responsePlayerDto = gameService.getWinner(playerPick);
        statisticServices.updateStatistic(userDetailsImpl, responsePlayerDto.getResult());

        model.addAttribute("responsePlayerDto", responsePlayerDto);

        return "rock-paper-scissors-result";
    }
}
