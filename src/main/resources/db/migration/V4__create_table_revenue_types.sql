CREATE TABLE revenue_types
(
    id   VARCHAR(36) NOT NULL PRIMARY KEY,
    code VARCHAR(36) NOT NULL,

    CONSTRAINT unique_revenue_type_code UNIQUE (code)
);