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
    guid             TEXT,
    created          timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated          timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP
)
