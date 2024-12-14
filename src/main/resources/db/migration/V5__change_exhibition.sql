ALTER TABLE exhibition
ALTER COLUMN start_date TYPE date
USING start_date::date;

ALTER TABLE exhibition
ALTER COLUMN end_date TYPE date
USING start_date::date;