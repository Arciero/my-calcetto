package com.dstech.mycalcetto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Table(name = "arena_table")
@Entity
@Data
public class Arena {
	
	// Attributi
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	private String fieldType;
	private boolean isOutdoor;
	@JsonIgnore
	@OneToMany(mappedBy = "arena")
	private List<Match> matches = new ArrayList<>();

}