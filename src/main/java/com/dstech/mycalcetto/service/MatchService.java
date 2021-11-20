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

	public void createMatch(CreateMatchForm formData){
		Match newMatch = new Match();
		newMatch.setDateTime(formData.getDateTime());
		newMatch.setPrivate(formData.isPrivate());
		newMatch.setStatus("active");
		try {
			Arena arena = arenaRepository.findById(formData.getIdArena()).get();
			newMatch.setArena(arena);
		}catch (Exception E){
			System.out.println("No Arena Found for Id:"+formData.getIdArena());
		}

		Team teamA = new Team();
		teamA.setType('A');
		teamA.setMatch(newMatch);
		teamA.setPlayers();
		Team teamB = new Team();
		teamB.setType('B');
	}

	
	
}
