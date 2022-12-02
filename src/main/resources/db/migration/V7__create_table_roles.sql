CREATE TABLE roles
(
    id   VARCHAR(36) NOT NULL PRIMARY KEY,
    code VARCHAR(36) NOT NULL,

    CONSTRAINT unique_role_code UNIQUE (code)
);