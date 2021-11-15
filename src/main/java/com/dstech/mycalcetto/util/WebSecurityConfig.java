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
        auth.inMemoryAuthentication()
                .withUser("A").password(passwordEncoder().encode("A")).roles("PLAYER")
                .and()
                .withUser("B").password(passwordEncoder().encode("A")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/Index.html")

                .defaultSuccessUrl("/api/matchs/available", true)
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")



                //aggiunta dei 2 ".antMatchers in basso
                /*
                http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/homePage/**").permitAll()
                .antMatchers("/").hasAnyAuthority("PLAYER", "ADMIN")
                .antMatchers("/new").hasAnyAuthority("PLAYER", "ADMIN")
                .antMatchers("/edit/**").hasAnyAuthority("PLAYER", "ADMIN")
                .antMatchers("/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //aggiunta del .defaultSuccessUrl
                   .defaultSuccessUrl("/api/matchs/available", true)
                    .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")

                 */
        ;
    }
}