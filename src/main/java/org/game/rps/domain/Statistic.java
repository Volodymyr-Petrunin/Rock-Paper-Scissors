package org.game.rps.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


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

    public Statistic() {
    }

    public Statistic(Long id, int totalGames, int wonGames, int lostGames, int drawGames) {
        this.id = id;
        this.totalGames = totalGames;
        this.wonGames = wonGames;
        this.lostGames = lostGames;
        this.drawGames = drawGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return totalGames == statistic.totalGames && wonGames == statistic.wonGames
                && lostGames == statistic.lostGames && drawGames == statistic.drawGames
                && Objects.equals(id, statistic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalGames, wonGames, lostGames, drawGames);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", totalGames=" + totalGames +
                ", wonGames=" + wonGames +
                ", lostGames=" + lostGames +
                ", drawGames=" + drawGames +
                '}';
    }
}
