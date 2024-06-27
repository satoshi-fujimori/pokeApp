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
),

(
   2,
   'フジギバナ',
   20,
   20,
   'http://www',
   'leaf',
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
),

(
   2,
   CURRENT_TIMESTAMP
);