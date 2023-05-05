CREATE TABLE IF NOT EXISTS user (
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
    FOREIGN KEY(user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS meal_day (
    id bigint NOT NULL AUTO_INCREMENT,
    day date DEFAULT NULL,
    id_diet_season bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(id_diet_season) REFERENCES diet_season(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS meal (
    id bigint NOT NULL AUTO_INCREMENT,
    id_meal_day bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_meal_day) REFERENCES meal_day(id)
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
    id_meal bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_meal) REFERENCES meal(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;