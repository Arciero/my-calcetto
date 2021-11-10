package com.dstech.mycalcetto.entity;




import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//ciao a tutti
@Table(name = "match_table")
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;
    private boolean isPrivate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "matchmaker_id", referencedColumnName = "id")
    private Player matchmaker;

    @ManyToOne
    @JoinColumn(name = "arena_id", referencedColumnName = "id")
    private Arena arena;


    @OneToMany(mappedBy = "match")
    private List<Team> teams = new ArrayList<>();

    public void setTeams(List<Team> teams) {
    	this.teams = teams;
    }
    
    public List<Team> getTeams(){
    	return teams;
    }
    
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}