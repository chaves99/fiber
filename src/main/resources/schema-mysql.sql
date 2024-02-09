CREATE TABLE IF NOT EXISTS fiber_user (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    email varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    height double DEFAULT NULL,
    weight double DEFAULT NULL,
    height_unit varchar(255) DEFAULT NULL,
    weight_unit varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS diet_season (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    active boolean DEFAULT NULL,
    calories_goal double DEFAULT NULL,
    carbohydrate_goal double DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    fat_goal double DEFAULT NULL,
    final_date date DEFAULT NULL,
    initial_date date DEFAULT NULL,
    protein_goal double DEFAULT NULL,
    user_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(user_id) REFERENCES fiber_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS meal (
    id bigint NOT NULL AUTO_INCREMENT,
    description VARCHAR(150) NOT NULL,
    day_time TIMESTAMP NOT NULL,
    meal_order INT NOT NULL,
    id_diet_season bigint NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS food (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    base_quantity double DEFAULT NULL,
    calories double DEFAULT NULL,
    carbohydrate double DEFAULT NULL,
    fiber double DEFAULT NULL,
    protein double DEFAULT NULL,
    fat double DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS food_per_meal (
    id_food bigint NOT NULL,
    id_meal bigint NOT NULL,
    quantity double NOT NULL,
    FOREIGN KEY (id_food) REFERENCES food(id),
    FOREIGN KEY (id_meal) REFERENCES meal(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;