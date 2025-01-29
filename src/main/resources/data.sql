-- Inserindo Autores -----------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO author (name, nationality)
VALUES ('J.K. Rowling', 'British');
INSERT INTO author (name, nationality)
VALUES ('George R.R. Martin', 'American');
INSERT INTO author (name, nationality)
VALUES ('J.R.R. Tolkien', 'British');
INSERT INTO author (name, nationality)
VALUES ('Masashi Kishimoto ', 'Japonese');

-- Inserindo Editoras -----------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO publisher (name, country)
VALUES ('Bloomsbury', 'UK');
INSERT INTO publisher (name, country)
VALUES ('Bantam Books', 'USA');
INSERT INTO publisher (name, country)
VALUES ('HarperCollins', 'UK');
INSERT INTO publisher (name, country)
VALUES ('NewPOP', 'UK');
INSERT INTO publisher (name, country)
VALUES ('Shueisha', 'Japan');


-- Inserindo Livros -----------------------------------------------------------------------------------------------------------------------------------------------
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
-----------------------------------------------------------------------------------------------------------------------------------------------

-- Inserindo Manga -----------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id)
VALUES ('Honzuki no gekokujou',
        'No is the name given to him by Ginny and the only name anyone knows, he is immune to the Spread.', 220,
        'English', 1, 1937, 'https://comicvine.gamespot.com/a/uploads/scale_avatar/11/117127/3973631-spread_no.jpg', 3);
INSERT INTO manga (id, magazine, is_on_going)
VALUES (4, '', false);

INSERT INTO catalog (title, description, pages, language, edition, year_of_release, publisher_id, image_url)
VALUES ('Naruto',
        'Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.',
        700, 'Japonês',
        2, 1999, 3,
        'http://books.google.com/books/content?id=THsEEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api');

-- Associando Autores aos Mangas
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (4, 4);



-----------------------------------------------------------------------------------------------------------------------------------------------

-- Inserindo Quadrinhos -----------------------------------------------------------------------------------------------------------------------------------------------

-- Associando Autores aos Mangas
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (1, 1);
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (2, 2);
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (3, 3);