CREATE TABLE IF NOT EXISTS fiber_user (
    id SERIAL NOT NULL,
    name varchar(255) DEFAULT NULL,
    email varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    height real DEFAULT NULL,
    weight real DEFAULT NULL,
    height_unit varchar(255) DEFAULT NULL,
    weight_unit varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS diet_season (
    id SERIAL NOT NULL,
    name varchar(255) DEFAULT NULL,
    active boolean DEFAULT NULL,
    calories_goal real DEFAULT NULL,
    carbohydrate_goal real DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    fat_goal real DEFAULT NULL,
    final_date date DEFAULT NULL,
    initial_date date DEFAULT NULL,
    protein_goal real DEFAULT NULL,
    user_id integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(user_id) REFERENCES fiber_user(id)
);

CREATE TABLE IF NOT EXISTS meal (
    id SERIAL NOT NULL,
    description VARCHAR(150) NOT NULL,
    day_time TIMESTAMP NOT NULL,
    meal_order INTEGER NOT NULL,
    id_diet_season INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS food (
    id SERIAL NOT NULL,
    name VARCHAR(255) DEFAULT NULL,
    base_quantity REAL DEFAULT NULL,
    calories REAL DEFAULT NULL,
    carbohydrate REAL DEFAULT NULL,
    fiber REAL DEFAULT NULL,
    protein REAL DEFAULT NULL,
    fat REAL DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS food_per_meal (
    id_food integer NOT NULL,
    id_meal integer NOT NULL,
    quantity real NOT NULL,
    FOREIGN KEY (id_food) REFERENCES food(id),
    FOREIGN KEY (id_meal) REFERENCES meal(id)
);