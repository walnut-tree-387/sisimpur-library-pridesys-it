-- Create User table
CREATE TABLE library_users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    delete_status VARCHAR(20) NOT NULL DEFAULT 'NO'
);

-- Create Author table
CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    biography TEXT,
    delete_status VARCHAR(20) NOT NULL DEFAULT 'NO'
);

-- Create Book table
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    author_id INT NOT NULL,
    published_year INT,
    genre VARCHAR(100),
    delete_status VARCHAR(20) NOT NULL DEFAULT 'NO',
    available_status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);
CREATE TABLE book_lent (
    id SERIAL PRIMARY KEY,

    book_id INT NOT NULL,
    library_user_id INT NOT NULL,

    CONSTRAINT fk_book_lent_book
        FOREIGN KEY (book_id)
        REFERENCES books (id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_book_lent_library_user
        FOREIGN KEY (library_user_id)
        REFERENCES library_users (id)
        ON DELETE RESTRICT
);


-- Insert sample users
INSERT INTO library_users (name, email, status, delete_status) VALUES
('Alice', 'alice@example.com', 'ACTIVE', 'NO'),
('Bob', 'bob@example.com', 'ACTIVE', 'NO');

-- Insert sample authors
INSERT INTO authors (name, biography, delete_status) VALUES
('J.K. Rowling', 'British author, best known for Harry Potter.', 'NO'),
('George R.R. Martin', 'American novelist and creator of Game of Thrones.', 'NO');

-- Insert sample books
INSERT INTO books (title, author_id, published_year, genre, available_status, delete_status) VALUES
('Harry Potter and the Sorcerer''s Stone', 1, 1997, 'Fantasy', 'AVAILABLE', 'NO'),
('Harry Potter and the Chamber of Secrets', 1, 1998, 'Fantasy', 'AVAILABLE', 'NO'),
('A Game of Thrones', 2, 1996, 'Epic Fantasy', 'AVAILABLE', 'NO'),
('A Clash of Kings', 2, 1998, 'Epic Fantasy', 'AVAILABLE', 'NO');

