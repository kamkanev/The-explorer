CREATE TABLE players (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(5) NOT NULL DEFAULT 'muj',
    coords VARCHAR(255) NOT NULL,
    skin VARCHAR(255) NOT NULL DEFAULT 'person',
    health INT NOT NULL DEFAULT 100,
    maxHealth INT NOT NULL DEFAULT 100,
    xp INT NOT NULL DEFAULT 0,
    level INT NOT NULL DEFAULT 0
);

CREATE TABLE worlds (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    mode VARCHAR(30) NOT NULL,
    seed VARCHAR(255) DEFAULT '',
    playerId INT NOT NULL UNIQUE,
    lastPlayed TIMESTAMP NOT NULL,
    CONSTRAINT FOREIGN KEY (playerId)
        REFERENCES players (id)
);

CREATE TABLE items (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    playerId INT NOT NULL,
    CONSTRAINT FOREIGN KEY (playerId)
        REFERENCES players (id)
);

CREATE TABLE entities (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    isAlive BOOLEAN NOT NULL DEFAULT FALSE,
    coords VARCHAR(255) NOT NULL,
    rotation VARCHAR(255) NOT NULL DEFAULT '',
    object VARCHAR(255) NOT NULL,
    texture VARCHAR(255) NOT NULL,
    ind INT,
    scale FLOAT NOT NULL,
    isNormal BOOLEAN NOT NULL DEFAULT FALSE,
    isAtlas int not null default 1,
    seeTh BOOLEAN DEFAULT FALSE,
    fakeLighting BOOLEAN DEFAULT FALSE,
    reflectivity FLOAT NOT NULL DEFAULT 0,
    shine FLOAT NOT NULL DEFAULT 10,
    worldId INT,
    CONSTRAINT FOREIGN KEY (worldId)
        REFERENCES worlds (id)
);