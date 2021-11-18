package com.dstech.mycalcetto.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.dstech.mycalcetto.entity.Arena;
import com.dstech.mycalcetto.entity.Player;
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
@RepositoryRestResource( collectionResourceRel = "matchs", path = "matchs")
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
	List<Match> findByisPrivateFalseAndDateTimeAfter(LocalDateTime dateOfYesterday);

	@Query ("select m from Match m " +
			"join m.teams t " +
			"join t.players p " +
			"where p.username=:username")
	List<Match> findByPlayer (String username);

}
