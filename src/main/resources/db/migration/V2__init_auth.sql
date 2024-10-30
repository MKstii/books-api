DROP TABLE file_model;
DROP TABLE book;

CREATE TABLE app_user (
    id SERIAL NOT NULL PRIMARY KEY,
    dtype VARCHAR(31) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_verified BOOLEAN,
    role VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    zip VARCHAR(255),
    fio VARCHAR(255)
);
