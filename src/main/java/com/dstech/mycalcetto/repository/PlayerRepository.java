package com.dstech.mycalcetto.repository;
/*
*elenco di import inutilizzati in questa pagina
import com.dstech.mycalcetto.entity.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
*/
import com.dstech.mycalcetto.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByUsername(String username);
}
