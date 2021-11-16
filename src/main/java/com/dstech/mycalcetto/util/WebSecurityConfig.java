package com.dstech.mycalcetto.util;

import com.dstech.mycalcetto.service.PlayerDetailsService;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
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
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/player/**").hasAuthority("PLAYER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                //.antMatchers("/").hasAnyAuthority("PLAYER", "ADMIN")
                //.antMatchers("/new").hasAnyAuthority("PLAYER", "ADMIN")
                //.antMatchers("/edit/**").hasAnyAuthority("PLAYER", "ADMIN")
                //.antMatchers("/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/index.html")
                    //.loginProcessingUrl("/index")
                    //.failureUrl("/index?error=true")
                    //.defaultSuccessUrl("HomePage.Html")
                    //.usernameParameter("user_name")
                    //.passwordParameter("password")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;
    }
}