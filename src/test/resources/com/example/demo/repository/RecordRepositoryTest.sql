DROP TABLE IF EXISTS monsters CASCADE;
DROP TABLE IF EXISTS records CASCADE;
CREATE TABLE monsters
(
   id SERIAL PRIMARY KEY,
   no INTEGER,
   name VARCHAR (50),
   height INTEGER,
   weight INTEGER,
   sprites VARCHAR (255),
   types VARCHAR (50),
   created_at TIMESTAMP WITHOUT TIME ZONE
);
CREATE TABLE records
(
   id SERIAL PRIMARY KEY,
   monster_id INTEGER REFERENCES monsters (id) ON DELETE CASCADE,
   created_at TIMESTAMP WITHOUT TIME ZONE
);
INSERT INTO monsters
(
   no,
   name,
   height,
   weight,
   sprites,
   types,
   created_at
)
VALUES
(
   1,
   'フシギダネ',
   7,
   69,
   'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png',
   'grass,poison',
   CURRENT_TIMESTAMP
);
INSERT INTO records
(
   monster_id,
   created_at
)
VALUES
(
   1,
   CURRENT_TIMESTAMP
);