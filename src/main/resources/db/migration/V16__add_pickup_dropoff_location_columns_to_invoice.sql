ALTER TABLE invoices
    ADD COLUMN pickup_location VARCHAR(36) NOT NULL,
    ADD COLUMN dropoff_location VARCHAR(36) NOT NULL;