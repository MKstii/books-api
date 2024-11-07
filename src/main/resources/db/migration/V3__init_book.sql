CREATE TABLE author (
   id SERIAL NOT NULL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   bio VARCHAR(255),
   birth_date date,
   death_date date,
   wikipedia VARCHAR(255)
);
COMMENT ON COLUMN author.id IS 'Идентификатор';
COMMENT ON COLUMN author.name IS 'ФИО автора';
COMMENT ON COLUMN author.bio IS 'Биография автора';
COMMENT ON COLUMN author.birth_date IS 'Дата рождения';
COMMENT ON COLUMN author.death_date IS 'Дата смерти';
COMMENT ON COLUMN author.wikipedia IS 'Ссылка на wikipedia';


CREATE TABLE book (
   id SERIAL NOT NULL PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   subtitle VARCHAR(255),
   first_publish_date date,
   description VARCHAR(255)
);
COMMENT ON TABLE book IS 'Книга';
COMMENT ON COLUMN book.title IS 'Наименование';
COMMENT ON COLUMN book.subtitle IS 'Подзаголовок';
COMMENT ON COLUMN book.first_publish_date IS 'Дата публикации';
COMMENT ON COLUMN book.description IS 'Описание';

CREATE TABLE books_authors (
   author_id    INTEGER NOT NULL REFERENCES author,
   book_id      INTEGER NOT NULL REFERENCES book
);

CREATE TABLE book_cover (
   id SERIAL NOT NULL PRIMARY KEY,
   path VARCHAR(255),
   book_id INTEGER REFERENCES book
);
COMMENT ON COLUMN book_cover.path IS 'Путь в яндекс диске';


CREATE TABLE subject (
   id SERIAL NOT NULL PRIMARY KEY,
   subject VARCHAR(255)
);


CREATE TABLE books_subjects (
   book_id BIGINT NOT NULL REFERENCES book,
   subject_id INTEGER NOT NULL REFERENCES subject
);


CREATE TABLE history (
   id SERIAL NOT NULL PRIMARY KEY,
   date_of_issue date,
   return_due_date date,
   return_date date,
   book_id BIGINT REFERENCES book,
   customer_id INTEGER REFERENCES customer
);
COMMENT ON TABLE history IS 'Заказ';
COMMENT ON COLUMN history.id IS 'Идентификатор';
COMMENT ON COLUMN history.date_of_issue IS 'Дата получения книги';
COMMENT ON COLUMN history.return_due_date IS 'Дата возвращения книги';
COMMENT ON COLUMN history.return_date IS 'Фактическая дата возвращения';