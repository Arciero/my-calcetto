package com.dstech.mycalcetto.repository;

import java.time.LocalDateTime;
import java.util.List;
/*
*elenco di import inutilizzati in questa pagina
import com.dstech.mycalcetto.entity.Arena;
import com.dstech.mycalcetto.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
*/
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dstech.mycalcetto.entity.Match;

/*
 * Usando l'annotazione @RepositoryRestResource al posto di @JpaRepository,
 * Spring crea automaticamente i servizi rest per tutti i metodi CRUD esposti da questo repository
 */
@RepositoryRestResource( collectionResourceRel = "matchs", path = "matchs")
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
	List<Match> findByisPrivateFalseAndDateTimeAfter(LocalDateTime dateOfYesterday);

	@Query (
			"SELECT m FROM Match m " +
			"JOIN m.teams t " +
			"JOIN t.players p " +
			"WHERE p.username=:username"
	)
	List<Match> myMatches (String username);

	List<Match> findByMatchmakerUsername(String username);

}
