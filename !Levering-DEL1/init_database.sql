

-- Bruker
CREATE TABLE bruker (
    bruker_id INT UNSIGNED NOT NULL,
    navn VARCHAR(64) NOT NULL,
    PRIMARY KEY (bruker_id)
);

-- Treningsøkt
CREATE TABLE treningsøkt (
    økt_id INT UNSIGNED NOT NULL,
    dato_tidspunkt DATETIME,
    varighet INT UNSIGNED,
    PRIMARY KEY (økt_id)
);

-- Bruker_økt
CREATE TABLE bruker_økt (
    bruker_id INT UNSIGNED NOT NULL,
    økt_id INT UNSIGNED NOT NULL,
    CONSTRAINT bruker_økt_FK1 FOREIGN KEY (bruker_id) REFERENCES bruker(bruker_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT bruker_økt_FK2 FOREIGN KEY (økt_id) REFERENCES treningsøkt(økt_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Notat
CREATE TABLE notat (
    økt_id INT UNSIGNED NOT NULL,
    formål TEXT,
    opplevelse TINYINT UNSIGNED,
    diverse TEXT,
    CONSTRAINT notat_FK1 FOREIGN KEY (økt_id) REFERENCES treningsøkt(økt_id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
	CHECK (opplevelse < 11)
);

-- Resultat
CREATE TABLE resultat (
    økt_id INT UNSIGNED NOT NULL,
    form TINYINT NOT NULL,
    prestasjon TINYINT NOT NULL,
    CONSTRAINT resultat_FK1 FOREIGN KEY (økt_id) REFERENCES treningsøkt(økt_id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
	CHECK (form < 11 AND prestasjon < 11)
);

-- Øvelse
CREATE TABLE øvelse (
    øvelse_id INT UNSIGNED NOT NULL,
    beskrivelse TEXT,
    fastmonter BOOLEAN,
    PRIMARY KEY (øvelse_id)
);

-- Økt_øvelse
CREATE TABLE økt_øvelse (
    øvelse_id INT UNSIGNED NOT NULL,
    økt_id INT UNSIGNED NOT NULL,
    Resultatlogg TEXT,
    CONSTRAINT økt_øvelse_FK1 FOREIGN KEY (øvelse_id) REFERENCES øvelse(øvelse_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT økt_øvelse_FK2 FOREIGN KEY (økt_id) REFERENCES treningsøkt(økt_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Logg
CREATE TABLE logg (
  økt_id INT UNSIGNED NOT NULL,
  øvelse_id INT UNSIGNED NOT NULL,
  sett INT,
  repetisjoner INT,
  kg INT,
  CONSTRAINT logg_FK1 FOREIGN KEY (økt_id) REFERENCES treningsøkt(økt_id)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
CONSTRAINT logg_FK2 FOREIGN KEY (øvelse_id) REFERENCES øvelse(øvelse_id)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

-- Øvelsesgruppe
CREATE TABLE øvelsesgruppe (
    øvelsesgruppe_id INT UNSIGNED NOT NULL,
    navn VARCHAR(64) NOT NULL,
    Beskrivelse TEXT,
    PRIMARY KEY (øvelsesgruppe_id)
);

-- Øvelse_gruppe
CREATE TABLE øvelse_gruppe (
    øvelse_id INT UNSIGNED NOT NULL,
    øvelsesgruppe_id INT UNSIGNED NOT NULL,

    CONSTRAINT øvelse_gruppe_FK1 FOREIGN KEY (øvelse_id) REFERENCES øvelse(øvelse_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT øvelse_gruppe_FK2 FOREIGN KEY (øvelsesgruppe_id) REFERENCES øvelsesgruppe(øvelsesgruppe_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);



-- Apparat
CREATE TABLE apparat (
    apparat_id INT UNSIGNED NOT NULL,
    navn VARCHAR(64) NOT NULL,
    brukerinstruks TEXT,
    PRIMARY KEY (apparat_id)
);

-- Øvelse_apparat
CREATE TABLE øvelse_apparat (
	apparat_id INT UNSIGNED NOT NULL,
    øvelse_id INT UNSIGNED NOT NULL,
    CONSTRAINT øvelse_apparat_FK1 FOREIGN KEY (apparat_id) REFERENCES apparat(apparat_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT øvelse_apparat_FK2 FOREIGN KEY (øvelse_id) REFERENCES øvelse(øvelse_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);
