package com.dstech.mycalcetto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

//Lombock Notation
@Data // is a convenient shortcut annotation that bundles the features of @ToString,
		// @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@AllArgsConstructor // genera il costruttore con tutti i parametri
@NoArgsConstructor // genera il costruttore vuoto

@Table(name = "player") // defisce il nome della tabella
@Entity // comunica a spring che si tratta di una classe che rappresenta un entita,
		// quindi una tabella, del database
public class Player {

	@Id // marca l'attributo come chiave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // strategia di definizione dell'attributo, in questo caso
														// autoincrementale
	private Long id;

	private String name;
	private String surname;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String email;
	private String password;
	@Column(unique = true)
	private String phoneNumber;
	@Column(name = "photo", columnDefinition = "BLOB")
	private byte[] photo;
	@JsonIgnore
	@OneToMany(mappedBy = "matchmaker")
	private List<Match> organizedMatches = new ArrayList<>();
	@ManyToMany(mappedBy = "players")
	private List<Team> teams = new ArrayList<>();

	// metodi
	public List<Match> getOrganizedMatches() {
		return organizedMatches;
	}

	public void setOrganizedMatches(List<Match> organizedMatches) {
		this.organizedMatches = organizedMatches;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}