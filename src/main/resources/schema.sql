create TABLE team
(
    id IDENTITY NOT NULL PRIMARY KEY,
    name   VARCHAR(200),
    slogan VARCHAR(500)
);

CREATE TABLE player
(
    id IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(200),
    last_name VARCHAR(200),
    birthday DATE,
    position VARCHAR(20),
    team_id INT,
    CONSTRAINT FK_Player FOREIGN KEY (team_id) REFERENCES team(id)
);