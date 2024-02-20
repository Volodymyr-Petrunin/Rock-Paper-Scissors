CREATE SEQUENCE player_seq START WITH 1 INCREMENT BY 100;

CREATE SEQUENCE statistic_seq START WITH 1 INCREMENT BY 100;

CREATE TABLE players
(
    player_id       BIGINT NOT NULL PRIMARY KEY,
    player_name     VARCHAR(255),
    player_password VARCHAR(255),
    statistic_id    BIGINT UNIQUE
);

CREATE TABLE statistics
(
    statistic_id          BIGINT NOT NULL PRIMARY KEY,
    statistic_total_games INT,
    statistic_won_games   INT,
    statistic_lost_games  INT,
    statistic_draw_games  INT
);

ALTER TABLE players
    ADD CONSTRAINT FK_PLAYER_ON_STATISTIC FOREIGN KEY (statistic_id) REFERENCES statistics (statistic_id);