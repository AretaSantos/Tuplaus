package com.tuplaus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class TuplausService {
	
	static Scanner Scanner = new Scanner(System.in);

	static String jdbcUrl;
	static String username;
	static String password;

	public static Game getPlayerInfo(Game game) {

		String propertiesFilePath = "src/main/resources/application.properties";
		Properties properties = ReadPropFile.getProperties(propertiesFilePath);

		jdbcUrl = properties.getProperty("spring.datasource.url");
		username = properties.getProperty("spring.datasource.username");
		password = properties.getProperty("spring.datasource.password");

		try {

			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			Statement statement = connection.createStatement();

			String sqlQuery = "SELECT balance, name, id FROM Player WHERE id = " + game.getPlayerId();

			ResultSet resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()) {
				game.setBalance(resultSet.getInt("balance"));
				game.setPlayerName(resultSet.getString("name"));
				game.setPlayerId(resultSet.getLong("id"));

				System.out.println("Pelaajan id: " + game.getPlayerId() + ", Nimi: " + game.getPlayerName() + ", Saldo: "
						+ game.getBalance());
			}

			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return game;
	}

	public static void playGame(Game game) throws SQLException {

		int[] intArray = null;

		boolean GameOn = true;
		
		System.out.println("Tervetuloa " + game.getPlayerName());
		
		enoughBalance(game);
		
		while (GameOn) {
			
			boolean validChoice = false;
			String choice = "";
			String continueGame = "";

			while (!validChoice) {

				System.out.println("Syötä valintasi (suuri tai pieni): ");
				choice = Scanner.next();

				if (choice.equalsIgnoreCase("suuri") || choice.equalsIgnoreCase("pieni")) {
					validChoice = true;

				}
			}

			if (choice.equals("suuri")) {

				intArray = new int[] { 8, 9, 10, 11, 12, 13 };

			} else {

				intArray = new int[] { 1, 2, 3, 4, 5, 6 };

			}
			
			game.setChoice(choice);

			Random randomNum = new Random();

			int cardNumber = randomNum.nextInt(13) + 1;

			game.setCard(cardNumber);

			System.out.println("Arvottu kortti: " +cardNumber);

			boolean found = false;

			if (cardNumber == 7) {
				
				game.setWin(false);

			} else {

				for (int i = 0; i < intArray.length; i++) {

					if (intArray[i] == cardNumber) {
						found = true;
						break;
					}
				}

			}

			if (found) {
				System.out.println("Voitit");
				game.setWin(true);
				int originalBalance = game.getBalance();
				int newBalance = originalBalance + game.getBet();
				game.setBalance(newBalance);

			} else {

				System.out.println("Hävisit");
				game.setWin(false);
				int originalBalance = game.getBalance();
				int newBalance = originalBalance - game.getBet();
				game.setBalance(newBalance);
			}

			System.out.println("Nykyinen saldo: " + game.getBalance());

			saveBalance(game);
			saveResult(game);

			System.out.print("Haluatko jatkaa pelaamista? (Y/N) ");

			continueGame = Scanner.next();

			if (continueGame.equalsIgnoreCase("n")) {

				GameOn = false;

			}

		}
		
		System.out.print("Haluatko sammuttaa pelin? (Y/N) ");

		String gameOff = Scanner.next();
		
		if(gameOff.equalsIgnoreCase("y")) {
			
			System.exit(0);

		}
		
	}

	public static void saveResult(Game game) throws SQLException {

		Connection conn = null;
		PreparedStatement preparedStatement = null;

		String propertiesFilePath = "src/main/resources/application.properties";
		Properties properties = ReadPropFile.getProperties(propertiesFilePath);

		jdbcUrl = properties.getProperty("spring.datasource.url");
		username = properties.getProperty("spring.datasource.username");
		password = properties.getProperty("spring.datasource.password");

		LocalDateTime timestamp = LocalDateTime.now();

		String sqlQuery = "INSERT INTO Game (timestamp, bet, choice, cardNumber, win, player_id) VALUES (?, ?, ?, ?, ?, ?)";

		conn = DriverManager.getConnection(jdbcUrl, username, password);

		try {

			try (PreparedStatement insertStatement = conn.prepareStatement(sqlQuery)) {
				insertStatement.setString(1, timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
				insertStatement.setInt(2, game.getBet());
				insertStatement.setString(3, game.getChoice());
				insertStatement.setInt(4, game.getCardNumber());
				insertStatement.setBoolean(5, game.getWin());
				insertStatement.setLong(6, game.getPlayerId());

				insertStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} finally {

			System.out.println("Pelin tiedot tallennettu tietokantaan.");

			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (conn != null) {
				conn.close();
			}

		}
		
		
}
	public static void saveBalance(Game game) throws SQLException {
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		String propertiesFilePath = "src/main/resources/application.properties";
		Properties properties = ReadPropFile.getProperties(propertiesFilePath);

		jdbcUrl = properties.getProperty("spring.datasource.url");
		username = properties.getProperty("spring.datasource.username");
		password = properties.getProperty("spring.datasource.password");
		
		conn = DriverManager.getConnection(jdbcUrl, username, password);
		
		String sqlQueryPlayer = "UPDATE Player set balance = " + game.getBalance() + " WHERE id = " + game.getPlayerId();

		try {

			try (PreparedStatement insertStatement = conn.prepareStatement(sqlQueryPlayer)) {

				insertStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}

	} finally {
		
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	}
	
	public static void enoughBalance(Game game) {
		
		  boolean enoughBalance = false;
		  int currentBalance = game.getBalance();
	        
	      int bet = 0;
	      
	      System.out.println("Syötä panos: ");
	        
				while (!enoughBalance) {

			        bet = Scanner.nextInt();

			        if (bet > currentBalance) {
			        	
			        	System.out.println("Tilillä liian vähän saldoa, tilin saldo: " + game.getBalance() + ". Syötä panos:");
			        	
			        } else {
			        	
			        	enoughBalance = true;
			        	currentBalance = currentBalance - bet;
			        }
				}
     
			try {
				
				saveBalance(game);
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
	        game.setBet(bet);
		
	}

}