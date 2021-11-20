package com.dstech.mycalcetto.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.dstech.mycalcetto.entity.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.dstech.mycalcetto.entity.Match;

/**
 * Usando l'annotazione  RepositoryRestResource} al posto di {@link JpaRepository},
 * Spring crea automaticamente i servizi rest per tutti i metodi CRUD esposti da questo repository
 */
@RepositoryRestResource( collectionResourceRel = "matches", path = "matches")
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
	List<Match> findByisPrivateFalseAndDateTimeAfter(LocalDateTime dateOfYesterday);

	@Query(value = "SELECT m.* FROM match_table m WHERE DATE(date_time) =:date AND arena_id = :id_Arena" , nativeQuery = true)
	public List<Match> findByDateAndArena(LocalDate date, Long id_Arena);
}
