CREATE TABLE author (
   id SERIAL NOT NULL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   bio VARCHAR(255),
   birth_date date,
   death_date date,
   wikipedia VARCHAR(255)
);
COMMENT ON COLUMN author.birth_date IS '???? ????????';
COMMENT ON COLUMN author.death_date IS '???? ??????';
COMMENT ON COLUMN author.wikipedia IS '?????? ?? ?????????';


CREATE TABLE book (
   id SERIAL NOT NULL PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   subtitle VARCHAR(255),
   first_publish_date date,
   description VARCHAR(255),
   book_cover_id BIGINT,
   book_subject_id BIGINT
);
COMMENT ON TABLE book IS '?????';


CREATE TABLE book_cover (
   id SERIAL NOT NULL PRIMARY KEY,
   mime_type VARCHAR(255),
   path VARCHAR(255),
   book_id INTEGER REFERENCES book
);
ALTER TABLE book ADD CONSTRAINT fk_book_on_book_cover FOREIGN KEY (book_cover_id) REFERENCES book_cover (id);

COMMENT ON COLUMN book_cover.mime_type IS 'MIME ???';
COMMENT ON COLUMN book_cover.path IS '???? ? ?????? ?????';


CREATE TABLE book_subject (
   id SERIAL NOT NULL PRIMARY KEY,
   subject VARCHAR(255)
);
ALTER TABLE book ADD CONSTRAINT fk_book_on_book_subject FOREIGN KEY (book_subject_id) REFERENCES book_subject (id);


CREATE TABLE books_authors (
   author_id INTEGER NOT NULL REFERENCES author,
   book_id INTEGER NOT NULL REFERENCES book
);


CREATE TABLE history (
   id SERIAL NOT NULL PRIMARY KEY,
   date_of_issue date,
   return_until date,
   return_date date,
   book_id BIGINT REFERENCES book,
   customer_id INTEGER REFERENCES app_user
);