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
   'フジギダネ',
   10,
   10,
   'http://www',
   'leaf',
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