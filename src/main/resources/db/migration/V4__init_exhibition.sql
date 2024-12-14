CREATE TABLE IF NOT EXISTS exhibition (
	id serial NOT NULL,
	name varchar(200) NULL,
	description varchar(500) NULL,
	start_date timestamp NULL,
	end_date timestamp NULL,
	CONSTRAINT exhibition_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS exhibition_book (
	id serial NOT NULL,
	exhibition_id int8 NOT NULL,
	book_id int8 NOT NULL,
	display_positon int NULL,
	notes text NULL,
	CONSTRAINT exhibition_book_pk PRIMARY KEY (id),
	CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book(id),
    CONSTRAINT fk_exhibition FOREIGN KEY (exhibition_id) REFERENCES exhibition(id)
);



