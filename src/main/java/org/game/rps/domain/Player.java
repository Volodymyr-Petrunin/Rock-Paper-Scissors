package org.game.rps.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "players")
@NamedEntityGraph(name = "player-entity-graph",
    attributeNodes = {
    @NamedAttributeNode("statistic")
})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(name, player.name) && Objects.equals(password, player.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}
