CREATE TABLE cars
(
    id                  VARCHAR(36) NOT NULL PRIMARY KEY,
    model               VARCHAR(36) NOT NULL,
    brand_id            VARCHAR(36) NOT NULL,
    year_of_manufacture INTEGER     NOT NULL,
    price               NUMERIC     NOT NULL,
    is_reserved         BOOLEAN     NOT NULL
);