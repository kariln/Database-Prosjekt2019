-- Some random data

INSERT INTO apparat VALUES (1,'romaskin','napp');
INSERT INTO apparat VALUES (2,'nedtrekk', 'dra ned');
INSERT INTO apparat VALUES (3, 'mølle', 'løp');
INSERT INTO apparat VALUES (4, 'beinpress','trøkk');
INSERT INTO apparat VALUES (5, 'smithmaskin', 'trøkk');

INSERT INTO treningsøkt VALUES (1, '2012-10-15 23:00:00', '17');
INSERT INTO treningsøkt VALUES (2, '2012-10-15 22:59:59', '32');
INSERT INTO treningsøkt VALUES (3, '2012-10-15 12:29:00', '310');
INSERT INTO treningsøkt VALUES (4, '2012-10-15 11:19:59', '130');

INSERT INTO notat VALUES (2, 'Pumpe for SK19', 3, 'Sykt smuud dag');

-- INSERT INTO resultat VALUES (3, 6, 8);

INSERT INTO øvelse VALUES (1, "Armhevninger", "trøkk", FALSE);
INSERT INTO øvelse VALUES (2, "Ro", "Napp", TRUE);
INSERT INTO øvelse VALUES (3, "Nedtrekk", "Dra", TRUE);

INSERT INTO økt_øvelse VALUES (2, 2, "Rodde sykt bra i dag");

-- INSERT INTO logg VALUES (2, 3, 3, 12, 85);

INSERT INTO øvelsesgruppe VALUES (1, "Bein", "Øvelse som omhandler setet, lår og legger.");
INSERT INTO øvelsesgruppe VALUES (2, "Mega", "Øvelse som omhandler mage.");
