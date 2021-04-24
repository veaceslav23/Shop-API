CREATE TABLE users
(
    id              VARCHAR(36)  NOT NULL PRIMARY KEY,
    username        VARCHAR(36)  NOT NULL,
    email           VARCHAR(100) NOT NULL,
    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status          VARCHAR(36)  NOT NULL,
    profile_picture VARCHAR(100) NOT NULL

        CONSTRAINT unique_username UNIQUE (username),
    CONSTRAINT unique_email UNIQUE (email)
);