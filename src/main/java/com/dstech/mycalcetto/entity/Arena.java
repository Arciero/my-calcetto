package com.dstech.mycalcetto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@Table(name = "arena_table")
@Entity
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

	// Metodi
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public boolean isOutdoor() {
		return isOutdoor;
	}

	public void setOutdoor(boolean isOutdoor) {
		this.isOutdoor = isOutdoor;
	}
}