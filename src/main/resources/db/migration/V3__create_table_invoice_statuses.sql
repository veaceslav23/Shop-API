CREATE TABLE invoice_statuses
(
    id   VARCHAR(36) NOT NULL PRIMARY KEY,
    code VARCHAR(36) NOT NULL,

    CONSTRAINT unique_invoice_status_code UNIQUE (code)
);