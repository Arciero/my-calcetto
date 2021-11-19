package com.dstech.mycalcetto.util;

import com.dstech.mycalcetto.service.PlayerDetailsService;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new PlayerDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    /*
    *tutta questa parte di codice non serve
    *
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);

        return authProvider;
    }
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())       //richiama PlayerDetailsService a riga 16
                .passwordEncoder(passwordEncoder());            //richiama BCryptPasswordEncoder a riga 21
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                //.antMatchers("/home").permitAll()
                //.antMatchers("/api/matches/**").permitAll()
                //.antMatchers("/").hasAnyAuthority("PLAYER", "ADMIN")
                //.antMatchers("/new").hasAnyAuthority("PLAYER", "ADMIN")
                //.antMatchers("/edit/**").hasAnyAuthority("PLAYER", "ADMIN")
                //.antMatchers("/delete/**").hasAuthority("ADMIN")
                //.loginProcessingUrl("/index")
                //.failureUrl("/index?error=true")
                //.usernameParameter("user_name")
                //.passwordParameter("password")
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/player/**").hasAuthority("PLAYER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/home", true) /* Questo chiamera' il view controller MatchController
                                                                                che poi rimandera' alla pagina che si chiama login */
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;
    }
}
