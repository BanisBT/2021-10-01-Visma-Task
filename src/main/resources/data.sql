DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS user_table;

CREATE TABLE user_table
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(255)          NOT NULL,
    books_taken INTEGER               NOT NULL,
    created     timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated     timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE book
(
    id               BIGSERIAL PRIMARY KEY NOT NULL,
    tittle           VARCHAR(255)          NOT NULL,
    author           VARCHAR(255)          NOT NULL,
    category         VARCHAR(255),
    language         VARCHAR(255)          NOT NULL,
    publication_date TIMESTAMP             NOT NULL,
    isbn             VARCHAR(25)           NOT NULL,
    is_taken         BOOLEAN               NOT NULL,
    created          timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated          timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO book (id, tittle, author, category, language, publication_date, isbn, is_taken)
VALUES (1, 'Gyvuliu ukis', 'George Orwell', 'politika','lietuviu', '1974-01-01', 'IMS-13-2345-4642', false),
       (2, '1984', 'George Orwell', 'fantastika','lietuviu', '1976-01-01', 'IMS-14-2345-4642', true),
       (3, 'Kabinetas 339', 'Dovydas ir Birute', 'politika','lietuviu', '2020-01-01', 'IMS-16-2345-4642', true);
