package com.dstech.mycalcetto.entity;




import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "match_table")
@Entity
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;
    private boolean isPrivate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "matchmaker_id", referencedColumnName = "id")
    private Player matchmaker;

    @ManyToOne
    @JoinColumn(name = "arena_id", referencedColumnName = "id")
    private Arena arena;

    @OneToMany(mappedBy = "match")
    private List<Team> teams = new ArrayList<>();

}