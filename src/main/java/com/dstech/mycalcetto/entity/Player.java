package com.dstech.mycalcetto.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Lombock Notation
@Data // is a convenient shortcut annotation that bundles the features of @ToString,
		// @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor

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
	@Lob
	@Column(name = "photo", columnDefinition = "BLOB")
	private byte[] photo;
	private boolean enabled;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "players_roles",
			joinColumns = @JoinColumn(name = "player_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "matchmaker")
	private List<Match> organizedMatches = new ArrayList<>();
	@ManyToMany(mappedBy = "players")
	private List<Team> teams = new ArrayList<>();

}