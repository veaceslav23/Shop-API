CREATE TABLE invoices
(
    id             VARCHAR(36) NOT NULL PRIMARY KEY,
    user_id        VARCHAR(36) NOT NULL,
    car_id         VARCHAR(36) NOT NULL,
    start_date     TIMESTAMP   NOT NULL,
    end_date       TIMESTAMP   NOT NULL,
    payment_amount NUMERIC     NOT NULL,
    revenue_type   VARCHAR(36) NOT NULL,
    invoice_status VARCHAR(36) NOT NULL,
    created_at     TIMESTAMP   NOT NULL,
    updated_at     TIMESTAMP   NOT NULL,

    CONSTRAINT fk_invoice_status FOREIGN KEY (invoice_status) REFERENCES invoice_statuses (code),
    CONSTRAINT fk_revenue_type FOREIGN KEY (revenue_type) REFERENCES revenue_types (code),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES cars (id)
);