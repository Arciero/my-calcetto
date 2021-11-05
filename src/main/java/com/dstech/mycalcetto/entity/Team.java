package com.dstech.mycalcetto.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "team")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private char type;
    @ManyToOne
    @JoinColumn(name="match_table_id", referencedColumnName = "id")
    private Match match;
    @ManyToMany
    @JoinTable(name = "team_player",
            joinColumns = @JoinColumn(name = "id_team"),
            inverseJoinColumns = @JoinColumn(name = "id_player"))
    List<Player> players = new ArrayList<>();
    
    // Metodi
    public void setPlayers(List<Player> players) {
    	this.players = players;
    }
    
    public List<Player> getPlayers(){
    	return players;
    }

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	public void setMatch(Match match) {
		this.match = match;
	}
	
	public Match getMatch() {
		return this.match;
	}
}