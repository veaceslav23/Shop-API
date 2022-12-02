ALTER TABLE invoices
    DROP CONSTRAINT fk_invoice_status,
    DROP CONSTRAINT fk_revenue_type,
    ADD CONSTRAINT fk_revenue_type FOREIGN KEY (revenue_type) REFERENCES revenue_types (id);