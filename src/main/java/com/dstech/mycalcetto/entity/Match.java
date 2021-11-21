package com.dstech.mycalcetto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "match_table")
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDateTime dateTime;
    @NonNull
    private boolean isPrivate;
    @NonNull
    private String status;

    @ManyToOne
    @JoinColumn(name = "matchmaker_id", referencedColumnName = "id")
    private Player matchmaker;

    @ManyToOne(fetch = FetchType.LAZY) //abilita il lazy loading
    @JoinColumn(name = "arena_id", referencedColumnName = "id")

    private Arena arena;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    private List<Team> teams = new ArrayList<>();

}