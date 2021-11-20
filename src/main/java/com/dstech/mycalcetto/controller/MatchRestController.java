package com.dstech.mycalcetto.controller;

import com.dstech.mycalcetto.entity.Match;
import com.dstech.mycalcetto.form.CreateMatchForm;
import com.dstech.mycalcetto.repository.ArenaRepository;
import com.dstech.mycalcetto.repository.MatchRepository;
import com.dstech.mycalcetto.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/matches")
public class MatchRestController {
    @Autowired
    private MatchService matchService;

    @Autowired
    private CreateMatchForm createMatchForm;

    @GetMapping("/date/{date}/{idArena}")
    public Map<Integer, Boolean> getByDateAndArena(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @PathVariable("idArena") Long idArena){
        return matchService.scheduleArena(date, idArena);
    }

    @PostMapping("/creatematch/{isPrivate}/{dateTime}/{idArena}")
    public String createMatch(@PathVariable("isPrivate") Boolean isPrivate, @PathVariable("dateTime") LocalDateTime date, @PathVariable("idArena") Long idArena){
        createMatchForm.setDateTime(date);
        createMatchForm.setPrivate(isPrivate);
        createMatchForm.setIdArena(idArena);
        //MatchService.
        return"succesCreation";
    }



}




