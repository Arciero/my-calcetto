package com.dstech.mycalcetto.controller;
/*
*elenco di import inutilizzati in questa pagina
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
*/

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.dstech.mycalcetto.entity.MyPlayerDetails;
import com.dstech.mycalcetto.repository.MatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
//con quest'annotazione lombock inietta un logger
@Slf4j
//@RequestMapping("/api/matches")
public class MatchController {

	@Autowired
	private ArenaRepository arenaRepository;


    //	Injection with constructor MatchService in MatchController
    private MatchService matchService;

    @Autowired
    private MatchRepository matchRepository;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    //@GetMapping("/available")
    //public List<Match> availableMatch () {
    @GetMapping("/home")
    public String availableMatch(Model model) {

		List<Arena> arenas = arenaRepository.findAll();
		model.addAttribute("listArenas", arenas);

        /*
         * Carico tutti i match disponibili
         */
        log.info("recupero match disponibili");
        List<Match> matches = matchService.availableMatches();
        model.addAttribute("available_matches", matches);
        LocalDateTime d = LocalDateTime.now();
        /*
         * Carico i match dell'utente loggato
         */
        // Utente loggato
        MyPlayerDetails userDetails = (MyPlayerDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        log.info("recupero i match a cui sto partecipando");
        List<Match> myMatches = matchRepository.myMatches(username, d);
        model.addAttribute("my_matches", myMatches);

        /*
         * Carico le partite organizzate da me
         */
        log.info("recupero i match organizzati da me");
        List<Match> byMatchmakerUsername = matchRepository.findByMatchmakerUsername(username);
        model.addAttribute("matches_organized_by_me", byMatchmakerUsername);

        log.info("recupero tutti i match che ho giocato o organizzato");
        List<Match> history = matchRepository.historyMatches(username, d);
        model.addAttribute("played_matches", history);
        //"available_matches", così come "my_matches", "match_organized_by_me" e "played_matches"
        //sono le chiavi che potrò usare nella pagina thymeleaf

        return "homePage";
        //return matchService.availableMatches();
    }
}
