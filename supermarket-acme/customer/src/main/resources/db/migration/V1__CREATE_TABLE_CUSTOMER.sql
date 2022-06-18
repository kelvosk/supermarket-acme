CREATE TABLE tb_customer (
     id serial PRIMARY KEY,
     customer_name VARCHAR (200) NOT NULL,
     customer_email VARCHAR (200) NOT NULL,
     customer_cpf VARCHAR(200) NOT NULL
);