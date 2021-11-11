package com.dstech.mycalcetto.repository;

import com.dstech.mycalcetto.entity.Match;
import com.dstech.mycalcetto.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByUsername(String username);
}
