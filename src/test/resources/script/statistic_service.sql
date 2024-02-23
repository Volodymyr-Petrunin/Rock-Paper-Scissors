TRUNCATE TABLE statistics CASCADE;

ALTER SEQUENCE statistic_seq RESTART WITH 1;

INSERT INTO statistics VALUES (nextval('statistic_seq'), 20, 10, 5, 5);
INSERT INTO players VALUES (nextval('player_seq'), 'Player', null, 1);
