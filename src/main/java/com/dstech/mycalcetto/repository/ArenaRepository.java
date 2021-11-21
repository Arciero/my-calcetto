package com.dstech.mycalcetto.repository;

import com.dstech.mycalcetto.entity.Arena;
import com.dstech.mycalcetto.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArenaRepository extends JpaRepository<Arena, Long>{

}
