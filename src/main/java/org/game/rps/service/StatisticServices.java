package org.game.rps.service;

import jakarta.transaction.Transactional;
import org.game.rps.domain.Result;
import org.game.rps.domain.Statistic;
import org.game.rps.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatisticServices {
    private final StatisticRepository repository;

    @Autowired
    public StatisticServices(StatisticRepository repository) {
        this.repository = repository;
    }

    public Statistic createDefaultStatistic() {
        Statistic statistic = new Statistic(null, 0, 0, 0, 0);
        repository.save(statistic);

        return statistic;
    }

    public void updateStatistic(UserDetailsImpl userDetails ,Result result){
        Statistic statistic = userDetails.getPlayer().getStatistic();

        updateByResult(statistic, result);

        repository.save(statistic);
    }

    private void updateByResult(Statistic statistic, Result result) {
        statistic.setTotalGames(statistic.getTotalGames() + 1);

        switch (result) {
            case WIN -> statistic.setWonGames(statistic.getWonGames() + 1);
            case LOSE -> statistic.setLostGames(statistic.getLostGames() + 1);
            case DRAW -> statistic.setDrawGames(statistic.getDrawGames() + 1);
        }
    }
}
