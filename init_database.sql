-- Create Diary/user (dagbok bruker)

-- Create Note (notat)
CREATE TABLE note (
    id INT NOT NULL,
    purpose TEXT,
    experience INT, -- 1-10 points¨
    other TEXT
);

-- Create Exercise (Treningsøkt)
CREATE TABLE workout (

);

-- Create Result (Resultat)
CREATE TABLE result (

);

--

-- Bruker
CREATE TABLE bruker (
    id INT NOT NULL,
    navn VARCHAR(64) NOT NULL
)

-- Bruker-økt
CREATE TABLE bruker-økt (
    bruker-id INT NOT NULL,
    økt-id INT NOT NULL
)

-- Treningsøkt
CREATE TABLE treningsøkt (
    økt-id INT NOT NULL,
    dato-tidspunkt DATETIME,
    varighet INT
)

-- Notat
CREATE TABLE notat (
    økt-id INT NOT NULL,
    formål TEXT,
    Opplevelse INT,
    div TEXT
)

-- Resultat
CREATE TABLE resultat (
    økt-id INT NOT NULL,
    form INT NOT NULL,
    prestasjon INT NOT NULL
)

-- Økt-øvelse
CREATE TABLE økt-øvelse (
    øvelse-id INT NOT NULL,
    økt-id INT NOT NULL
)

-- Øvelse
CREATE TABLE øvelse (
    øvelse-id INT NOT NULL,
    øvelsesgruppe-id INT NOT NULL
)

-- Øvelse-gruppe
CREATE TABLE øvelse-gruppe (
    øvelse-id INT NOT NULL,
    øvelsesgruppe-id INT NOT NULL
)


-- Øvelsesgruppe
CREATE TABLE øvelsesgruppe (
    øvelsesgruppe-id INT NOT NULL,
    navn VARCHAR(64) NOT NULL,
    Beskrivelse TEXT
)

-- Ikke_fastmontert_øvelse

-- Fastmontert-øvelse

-- Apparat
