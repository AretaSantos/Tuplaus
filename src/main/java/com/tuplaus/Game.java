package com.tuplaus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

//Game object

@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long GameId;
	
	private String timestamp;
	private int bet;
	private String choice;
	private int cardNumber;
	private boolean win;
	private Long PlayerId;
	private int balance;
	private String playerName;
	
	@ManyToOne
	@JoinColumn(name = "player")
	private Player player;
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getInput() {
		return bet;
	}
	public void setInput(int input) {
		this.bet = input;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public int getCard() {
		return cardNumber;
	}
	public void setCard(int card) {
		this.cardNumber = card;
	}
	public boolean getWin() {
		return win;
	}
	public void setWin(boolean winTotal) {
		this.win = winTotal;
	}
	
	public  Long getPlayerId() {
		return PlayerId;
	}

	public void setPlayerId(Long userId) {
		PlayerId = userId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	
}
