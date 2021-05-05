CREATE TABLE cars
(
    id                  VARCHAR(36) NOT NULL PRIMARY KEY,
    model               VARCHAR(36) NOT NULL,
    brand_id            VARCHAR(36) NOT NULL,
    year_of_manufacture INTEGER     NOT NULL,
    price               NUMERIC     NOT NULL,
    is_reserved         BOOLEAN     NOT NULL,
    image_id            VARCHAR(36),

    CONSTRAINT fk_brand_id FOREIGN KEY (brand_id) REFERENCES brands (id),
    CONSTRAINT fk_image_id FOREIGN KEY (image_id) REFERENCES images (id)
);