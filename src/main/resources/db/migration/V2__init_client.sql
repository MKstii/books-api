CREATE TABLE customer(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    zip VARCHAR(255),
    fio VARCHAR(255)
);
COMMENT ON TABLE customer IS 'Клиент';
COMMENT ON COLUMN customer.id IS 'Идентификатор';
COMMENT ON COLUMN customer.name IS 'ФИО';
COMMENT ON COLUMN customer.address IS 'Адрес';
COMMENT ON COLUMN customer.city IS 'Город';
COMMENT ON COLUMN customer.phone IS 'Телефон';
COMMENT ON COLUMN customer.email IS 'Почта';
COMMENT ON COLUMN customer.zip IS 'Почтовый код';