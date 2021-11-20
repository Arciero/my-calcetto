package com.dstech.mycalcetto.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.dstech.mycalcetto.entity.Arena;
import com.dstech.mycalcetto.entity.Player;
import com.dstech.mycalcetto.repository.ArenaRepository;
import com.dstech.mycalcetto.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dstech.mycalcetto.entity.Match;
import com.dstech.mycalcetto.service.MatchService;

@Controller
@RequestMapping("/api/matches")
public class MatchController {
	@Autowired
	private ArenaRepository arenaRepository;

//	Injection with constructor MatchService in MatchController
	private MatchService matchService;
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}
//	
	@GetMapping("/available")
	public String availableMatch (Model model){
		List<Match> matches = matchService.availableMatches();
		model.addAttribute("available_matches", matches);
		//test 1
		//test 2
		List<Arena> arenas = arenaRepository.findAll();
		model.addAttribute("listArenas", arenas);
		//List<String> listArenas = Arrays.asList("Campo A", "Campo B", "Campo C", "Campo D", "Campo E");
		//model.addAttribute("listArenas", listArenas);
		return "homePage";
	}

}
