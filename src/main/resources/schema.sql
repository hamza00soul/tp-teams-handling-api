create TABLE team
(
    id IDENTITY NOT NULL PRIMARY KEY,
    name   VARCHAR(200),
    slogan VARCHAR(500)
);


CREATE TABLE player
(
    id IDENTITY NOT NULL PRIMARY KEY,
    firstname VARCHAR(200),
    lastname VARCHAR(200),
    birthday DATE,
    position VARCHAR(20)
);
