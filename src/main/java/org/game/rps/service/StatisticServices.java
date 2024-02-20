package org.game.rps.service;

import org.game.rps.domain.Statistic;
import org.game.rps.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
}
