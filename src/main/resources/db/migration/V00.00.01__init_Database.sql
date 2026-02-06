CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(100),
    price NUMERIC(10,2) NOT NULL,
    stock_quantity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE countries (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(100) NOT NULL UNIQUE,
                             code VARCHAR(10) NOT NULL UNIQUE
  );

CREATE TABLE cities (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        country_id BIGINT NOT NULL,
                        CONSTRAINT fk_city_country
                            FOREIGN KEY (country_id)
                                REFERENCES countries (id)
                                ON DELETE CASCADE
);

CREATE TABLE municipalities (
                                id BIGSERIAL PRIMARY KEY,
                                name VARCHAR(100) NOT NULL,
                                city_id BIGINT NOT NULL,
                                CONSTRAINT fk_municipality_city
                                    FOREIGN KEY (city_id)
                                        REFERENCES cities (id)
                                        ON DELETE CASCADE
);