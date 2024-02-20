package org.game.rps.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player {
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", sequenceName = "player_seq", allocationSize = 100)
    private Long id;
    @Column(name = "player_name")
    private String name;
    @Column(name = "player_password")
    private String password;
    @OneToOne
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;
}
