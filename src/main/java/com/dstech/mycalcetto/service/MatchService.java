package com.dstech.mycalcetto.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

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
	
	public List<Match> availableMatchs(){
		List<Match> matchs = matchRepository.findByisPrivateFalseAndDateTimeAfter(LocalDateTime.now().minusDays(1));
		Iterator<Match> it = matchs.iterator();
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
		return matchs;
	}
	
	
}
