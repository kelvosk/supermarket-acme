ALTER TABLE tb_fraud ADD customer_name VARCHAR(200);

ALTER TABLE tb_fraud ADD CONSTRAINT cpf_name UNIQUE (customer_cpf, customer_name);