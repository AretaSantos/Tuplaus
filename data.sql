CREATE TABLE IF NOT EXISTS  User(
	id INT PRIMARY KEY
	name VARCHAR (255) NOT NULL,
	balance INT NOT NULL
	
);

INSERT INTO User (id, name, balance) values (1, 'pelaaja1', 100);
