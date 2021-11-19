package com.dstech.mycalcetto.service;

import com.dstech.mycalcetto.entity.MyPlayerDetails;
import com.dstech.mycalcetto.entity.Player;
import com.dstech.mycalcetto.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerDetailsService implements UserDetailsService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Player user = playerRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyPlayerDetails(user);
    }

    public void createPlayer(Player user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled( true ); //una volta effettuata la registrazione l'account verrà abilitato
        playerRepository.save(user);
    }

}

