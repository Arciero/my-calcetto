package com.dstech.mycalcetto.repository;

import com.dstech.mycalcetto.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
