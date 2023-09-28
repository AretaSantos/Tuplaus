CREATE TABLE IF NOT EXISTS Player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    balance INT NOT NULL
);


CREATE TABLE IF NOT EXISTS Game (
	GameId INT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP DEFAULT '0000-00-00 00:00:00',
    bet int NOT  NULL,
    choice varchar (255) NOT NULL,
    cardNumber int NOT NULL,
    win int NOT NULL,
    player_id INT,
    CONSTRAINT fk_user_id FOREIGN KEY (player_id) REFERENCES Player (id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO Player (id, name, balance) VALUES (1, 'pelaaja1', 100);
INSERT INTO Player (id, name, balance) VALUES (2, 'pelaaja2', 100);
