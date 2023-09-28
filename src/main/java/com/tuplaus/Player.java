package com.tuplaus;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

//user object

@Entity
public class Player {
	
	@Id
	private Long id;
	
	private String name;
	private int balance;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
	private List<Game> games;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	

}
