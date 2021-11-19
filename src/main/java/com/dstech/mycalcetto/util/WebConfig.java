package com.dstech.mycalcetto.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {                   //ha le stesse funzioni di un metodo nel controller
        registry.addViewController("/login").setViewName("login");          //che ha come percorso "/login" e restituisce la pagina di nome "login" nel templates
    }

    /*
    *questo semplice metodo lo sposto in WebSecurityConfig
    *
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     */
}
