package com.dstech.mycalcetto.repository;

import com.dstech.mycalcetto.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository <Team, Long> {

}
