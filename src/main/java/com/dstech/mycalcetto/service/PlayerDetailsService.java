package com.dstech.mycalcetto.service;

import com.dstech.mycalcetto.entity.MyPlayerDetails;
import com.dstech.mycalcetto.entity.Player;
import com.dstech.mycalcetto.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlayerDetailsService implements UserDetailsService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Player user = playerRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyPlayerDetails(user);
    }

    public void createPlayer(UserDetails user) { // Domande: si può usare un interfaccia come tipo??
        playerRepository.save((Player) user);
    }

}

