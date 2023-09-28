package com.tuplaus;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication  
public class TuplausApplication {
	
	static Scanner Scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {

		SpringApplication.run(TuplausApplication.class, args);
		
		Game game = new Game();
		
		System.out.println("Tervetuloa pelaamaan Tuplausta!");
		
		System.out.println("Syötä pelaajan id: ");
		
		int id = Scanner.nextInt();
		
		game.setPlayerId(id);
		
		game = TuplausService.getPlayerInfo(game);
	
		TuplausService.playGame(game);
		
	
	}


}
