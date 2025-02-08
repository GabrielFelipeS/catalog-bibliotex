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
INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id,
                     has_adaptation, num_readers, publication_status, classification, is_active)
VALUES ('Harry Potter and the Philosopher''s Stone', 'First book of the Harry Potter series', 223, 'English', 1, 1997,
        'http://books.google.com/books/content?id=Gz8t2MttEQUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api',
        1, true, 0, 'COMPLETED', 'EVERYONE', true);
INSERT INTO book (id, isbn)
VALUES (1, '9780747532699');

INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id,
                     has_adaptation, num_readers, publication_status, classification, is_active)
VALUES ('A Game of Thrones', 'First book of A Song of Ice and Fire', 694, 'English', 1, 1996,
        'http://books.google.com/books/content?id=DLKMDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api',
        2, true, 0, 'COMPLETED', 'EVERYONE', true);
INSERT INTO book (id, isbn)
VALUES (2, '9780553103540');

INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id,
                     has_adaptation, num_readers, publication_status, classification, is_active)
VALUES ('The Hobbit', 'Fantasy novel by J.R.R. Tolkien', 310, 'English', 1, 1937,
        'http://books.google.com/books/content?id=OlCHcjX0RT4C&printsec=frontcover&img=1&zoom=1&source=gbs_api', 3,
        true, 0, 'COMPLETED', 'EVERYONE', true);
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
INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id,
                     has_adaptation, num_readers, publication_status, classification, is_active)
VALUES ('Ascendance of a Bookworm',
        'No is the name given to him by Ginny and the only name anyone knows, he is immune to the Spread.', 220,
        'English', 1, 1937, 'https://comicvine.gamespot.com/a/uploads/scale_avatar/11/117127/3973631-spread_no.jpg', 3,
        true, 0, 'COMPLETED', 'EVERYONE', true);
INSERT INTO manga (id, original_title)
VALUES (4, 'Honzuki no gekokujou');

INSERT INTO catalog (title, description, pages, language, edition, year_of_release, publisher_id, image_url,
                     has_adaptation, num_readers, publication_status, classification, is_active)
VALUES ('Naruto',
        'Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.',
        700, 'Japonês',
        1, 1999, 3,
        'http://books.google.com/books/content?id=THsEEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api',
        true, 0, 'COMPLETED', 'EVERYONE', true);
INSERT INTO manga (id, original_title)
VALUES (5, 'Naruto');

-- Associando Autores aos Mangas
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (4, 4);

-----------------------------------------------------------------------------------------------------------------------------------------------

-- Inserindo Quadrinhos -----------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO catalog (title, description, pages, language, edition, year_of_release, image_url, publisher_id,
                     has_adaptation, num_readers, publication_status, classification, is_active)
VALUES ('Batman',
        'No is the name given to him by Ginny and the only name anyone knows, he is immune to the Spread.',
        100,
        'English',
        1,
        2015,
        'https://comicvine.gamespot.com/a/uploads/original/11/117127/3973631-spread_no.jpg',
        1, true, 0, 'COMPLETED', 'EVERYONE', true);
INSERT INTO comic (id, universe)
VALUES (6, 'DC');

-- Associando Autores aos Mangas
INSERT INTO catalog_authors (catalog_id, author_id)
VALUES (5, 1);


-----------------------------------------------------------------------------------------------------------------------------------------------

-- Inserindo Gênero -----------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO genre (name)
VALUES ('Ação'),
       ('Aventura'),
       ('Comédia'),
       ('Drama'),
       ('Fantasia'),
       ('Ficção Científica'),
       ('Mistério'),
       ('Romance'),
       ('Suspense'),
       ('Terror'),
       ('Thriller'),
       ('Histórico'),
       ('Super-herói'),
       ('Cyberpunk'),
       ('Steampunk'),
       ('Space Opera'),
       ('Distopia'),
       ('Slice of Life'),
       ('Musical'),
       ('Esportes'),
       ('Crime'),
       ('Guerra'),
       ('Sobrenatural'),
       ('Western'),
       ('Noir'),
       ('Pós-apocalíptico'),
       ('Psicológico'),
       ('Dark Fantasy'),
       ('Magia'),
       ('Mecha'),
       ('Isekai'),
       ('Shounen'),
       ('Shoujo'),
       ('Seinen'),
       ('Josei'),
       ('Yaoi'),
       ('Yuri'),
       ('Harem'),
       ('Reverse Harem'),
       ('Ecchi'),
       ('Horror Cósmico'),
       ('Mitologia'),
       ('Espionagem'),
       ('Faroeste'),
       ('Militar'),
       ('Zumbis'),
       ('Vampiros'),
       ('Lobisomens'),
       ('Viagem no Tempo'),
       ('Drama Médico'),
       ('Drama Jurídico'),
       ('Policial'),
       ('Drama Familiar'),
       ('Reality Show'),
       ('Biográfico'),
       ('Paródia'),
       ('Gastronomia'),
       ('Documentário'),
       ('Infantil'),
       ('Musical'),
       ('Idol'),
       ('Boys Love (BL)'),
       ('Girls Love (GL)'),
       ('Stealth'),
       ('Artes Marciais'),
       ('Gangues'),
       ('Batalha Escolar'),
       ('Investigação'),
       ('Tecnologia'),
       ('Mitológico'),
       ('Dorama'),
       ('Ciberterrorismo'),
       ('Survival'),
       ('Drama Político');
