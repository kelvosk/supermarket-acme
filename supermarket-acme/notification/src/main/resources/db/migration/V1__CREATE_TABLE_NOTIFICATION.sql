CREATE TABLE tb_notification (
    id serial PRIMARY KEY,
    customer_cpf VARCHAR (20),
    sent_at TIMESTAMP,
    fraud BOOLEAN,
    message VARCHAR(200),
    sender VARCHAR(200),
    customer_email VARCHAR(200)
);