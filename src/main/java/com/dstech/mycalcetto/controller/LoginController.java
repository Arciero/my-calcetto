package com.dstech.mycalcetto.controller;

/*
*elenco di import inutilizzati in questa pagina
import com.dstech.mycalcetto.entity.MyPlayerDetails;
import com.dstech.mycalcetto.entity.Player;
import com.dstech.mycalcetto.service.PlayerDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;


import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

/*
*sposto queste due annotazioni nella nuova classe RegistrationController
    @Autowired
    private PlayerDetailsService playerDetailsService;

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") Player user) {
        playerDetailsService.createPlayer(user);
        System.out.println("ciao");
        return "HomePage";
    }
    */


//    @GetMapping("/home")
//    public String home(){ return "homePage";}
//
//    @GetMapping(value = {"/","/index"})
//    public String indexVoid() {
//        return "index";
//    }
//
//    /*@GetMapping("/login")
//    public String login(HttpServletRequest request, HttpSession session) {
//        session.setAttribute(
//                "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
//        );
//        return "index";
//    }*/

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }
        return error;
    }
}
