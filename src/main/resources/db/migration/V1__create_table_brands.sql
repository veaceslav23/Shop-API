CREATE TABLE brands(
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(36) NOT NULL,

    CONSTRAINT unique_brand_name UNIQUE (name)
);