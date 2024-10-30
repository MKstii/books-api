CREATE TABLE IF NOT EXISTS book (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    CONSTRAINT pk_book PRIMARY KEY (id)
);
COMMENT ON TABLE book IS 'Книги';

CREATE TABLE IF NOT EXISTS file_model (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    mime_type VARCHAR(255),
    path VARCHAR(255),
    book_id BIGINT,
    CONSTRAINT pk_file_model PRIMARY KEY (id)
);
COMMENT ON COLUMN file_model.mime_type IS 'MIME type';
COMMENT ON COLUMN file_model.path IS 'Путь в яндекс диске';

ALTER TABLE file_model ADD CONSTRAINT fk_file_model_on_book FOREIGN KEY (book_id) REFERENCES book (id);