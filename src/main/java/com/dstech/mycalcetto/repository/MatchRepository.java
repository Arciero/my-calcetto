package com.dstech.mycalcetto.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dstech.mycalcetto.entity.Match;

@Repository
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
	List<Match> findByisPrivateFalseAndDateTimeAfter(LocalDateTime dateOfYesterday);
	
}
