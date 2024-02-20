package org.game.rps.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "statistics")
public class Statistic {
    @Id
    @Column(name = "statistic_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "statistic_seq")
    @SequenceGenerator(name = "statistic_seq", sequenceName = "statistic_seq", allocationSize = 100)
    private Long id;
    @Column(name = "statistic_total_games")
    private int totalGames;
    @Column(name = "statistic_won_games")
    private int wonGames;
    @Column(name = "statistic_lost_games")
    private int lostGames;
    @Column(name = "statistic_draw_games")
    private int drawGames;
}
