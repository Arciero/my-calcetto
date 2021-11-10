package com.dstech.mycalcetto.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "team")
@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private char type;
    @ManyToOne
    @JoinColumn(name="match_table_id", referencedColumnName = "id")
    private Match match;
    @ManyToMany
    @JoinTable(name = "team_player",
            joinColumns = @JoinColumn(name = "id_team"),
            inverseJoinColumns = @JoinColumn(name = "id_player"))
    private List<Player> players = new ArrayList<>();
}