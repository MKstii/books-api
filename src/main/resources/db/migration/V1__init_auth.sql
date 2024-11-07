CREATE TABLE app_user (
    id SERIAL NOT NULL PRIMARY KEY,
    dtype VARCHAR(31) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_verified BOOLEAN,
    role VARCHAR(255) NOT NULL,
    fio VARCHAR
);