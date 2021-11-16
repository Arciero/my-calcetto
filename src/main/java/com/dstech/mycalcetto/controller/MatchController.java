package com.dstech.mycalcetto.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dstech.mycalcetto.entity.Match;
import com.dstech.mycalcetto.service.MatchService;

@Controller
@RequestMapping("/api/matches")
public class MatchController {
	
//	Injection with constructor MatchService in MatchController
	private MatchService matchService;
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}
//	
	@GetMapping("/available")
	//public List<Match> availableMatch () {
	public String availableMatch (Model model){

		List<Match> matches = matchService.availableMatches();
		model.addAttribute("available_matches", matches);
		return "homePage";
		//return matchService.availableMatches();
	}
}
