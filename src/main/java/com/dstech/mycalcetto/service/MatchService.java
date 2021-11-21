package com.dstech.mycalcetto.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dstech.mycalcetto.entity.Arena;
import com.dstech.mycalcetto.form.CreateMatchForm;
import com.dstech.mycalcetto.repository.ArenaRepository;
import com.dstech.mycalcetto.repository.PlayerRepository;
import com.dstech.mycalcetto.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstech.mycalcetto.entity.Match;
import com.dstech.mycalcetto.entity.Player;
import com.dstech.mycalcetto.entity.Team;
import com.dstech.mycalcetto.repository.MatchRepository;

import lombok.Data;

@Data
@Service
public class MatchService {
	
	private MatchRepository matchRepository;
	
	public MatchService(MatchRepository matchRepository){
		this.matchRepository= matchRepository;
	}

	@Autowired
	ArenaRepository arenaRepository;
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	TeamRepository teamRepository;
	
	public List<Match> availableMatches(){
		List<Match> matches = matchRepository.findByisPrivateFalseAndDateTimeAfter(LocalDateTime.now().minusDays(1));
		Iterator<Match> it = matches.iterator();
		while( it.hasNext() ) {
			Match m = it.next();
			List<Team> t =  m.getTeams();
			int currentParticipant = 0;
			for(int j = 0; j<t.size(); j++) {
				currentParticipant +=  t.get(j).getPlayers().size();
			}
			// int currentParticipant = t.get(0).getPlayers().size() + t.get(1).getPlayers().size();
			if(currentParticipant >= 10) {
				it.remove();
			}
		}
		return matches;
	}

	public Map<Integer, Boolean> scheduleArena(LocalDate bookingDay, Long idArena){
		List<Match> bookedTimes = matchRepository.findByDateAndArena(bookingDay, idArena);
		Map<Integer, Boolean> schedule = new HashMap<>();
		for (Match m: bookedTimes) {
			m.getDateTime().getHour();
			schedule.put(m.getDateTime().getHour(), true);
		}
		return schedule;
	}

	public String createMatch(CreateMatchForm formData){
		Match newMatch = new Match();
		// qui metto il giocatore della sessione come matchmaker, sostituire da "playerRepository.findById(1l).get()"
		newMatch.setMatchmaker(playerRepository.findById(1l).get());
		//
		newMatch.setDateTime(formData.getDateTime());
		newMatch.setPrivate(formData.getIsPrivate());
		System.out.println(newMatch.isPrivate());
		newMatch.setStatus("active");
		// set Arena
		try {
			Arena arena = arenaRepository.findById(formData.getIdArena()).get();
			newMatch.setArena(arena);
		}catch (Exception E){
			System.out.println("No Arena Found for Id:"+formData.getIdArena());
			return "Arena non trovata";
		}
		matchRepository.save(newMatch);
		// se la partita è pubblica crea le squadre altrimenti no
		if(!formData.getIsPrivate()) {
			// Create Void Teams
			Team teamA = new Team();
			teamA.setType('A');
			teamA.setMatch(newMatch);
			// qui metto il giocatore della sessione dentro uno dei team, sostituire da "playerRepository.findById(1l).get()"
			teamA.getPlayers().add(playerRepository.findById(1l).get());
			//
			Team teamB = new Team();
			teamB.setMatch(newMatch);
			teamB.setType('B');
			teamRepository.save(teamA);
			teamRepository.save(teamB);
		}

		return "Match Inserito con Successo";
	}

	
	
}
