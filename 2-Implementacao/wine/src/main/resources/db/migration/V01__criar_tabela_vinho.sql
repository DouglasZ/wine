CREATE TABLE vinho (
	codigo serial PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	tipo VARCHAR(50) NOT NULL,
	safra INT NOT NULL,
	volume INT NOT NULL,
	valor DECIMAL(10,2) NOT NULL
)