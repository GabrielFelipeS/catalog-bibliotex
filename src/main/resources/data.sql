-- Inserindo Autores
INSERT INTO author (name, nationality)
VALUES ('J.K. Rowling', 'British');
INSERT INTO author (name, nationality)
VALUES ('George R.R. Martin', 'American');
INSERT INTO author (name, nationality)
VALUES ('J.R.R. Tolkien', 'British');

-- Inserindo Editoras
INSERT INTO publisher (name, country)
VALUES ('Bloomsbury', 'UK');
INSERT INTO publisher (name, country)
VALUES ('Bantam Books', 'USA');
INSERT INTO publisher (name, country)
VALUES ('HarperCollins', 'UK');

-- Inserindo Livros
INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id)
VALUES ('Harry Potter and the Philosopher''s Stone', 'First book of the Harry Potter series', 223, 'English', 1, 1997,
        'url_to_image', 1);
INSERT INTO book (id, isbn)
VALUES (1, '9780747532699');

INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id)
VALUES ('A Game of Thrones', 'First book of A Song of Ice and Fire', 694, 'English', 1, 1996, 'url_to_image', 2);
INSERT INTO book (id, isbn)
VALUES (2, '9780553103540');

INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id)
VALUES ('The Hobbit', 'Fantasy novel by J.R.R. Tolkien', 310, 'English', 1, 1937, 'url_to_image', 3);
INSERT INTO book (id, isbn)
VALUES (3, '9780261102217');

-- Associando Autores aos Livros
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (1, 1);
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (2, 2);
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (3, 3);