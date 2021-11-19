package com.dstech.mycalcetto.controller;

import com.dstech.mycalcetto.entity.Player;
import com.dstech.mycalcetto.service.PlayerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private PlayerDetailsService playerDetailsService;

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new Player());
        return "userRegistration";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") Player user) {
        playerDetailsService.createPlayer(user);
        return "redirect:/home";
    }

}
