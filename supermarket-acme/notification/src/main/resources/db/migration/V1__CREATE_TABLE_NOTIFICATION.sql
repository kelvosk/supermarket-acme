CREATE TABLE tb_notification (
    id serial PRIMARY KEY,
    sent_at TIMESTAMP,
    id_customer NUMERIC,
    message VARCHAR(200),
    sender VARCHAR(200),
    customer_mail VARCHAR(200)
);