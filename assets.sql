.open mli-asset-management.db

DROP TABLE assets;
DROP TABLE users;
DROP TABLE units;

CREATE TABLE units (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL UNIQUE,
    creation_date TIMESTAMP NOT NULL,
    closed_date TIMESTAMP
);

CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL, 
    hire_date TIMESTAMP NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT 1,
    is_admin BOOLEAN NOT NULL,
    resignation_date TIMESTAMP,
    password TEXT NOT NULL,
    unit_id INTEGER NOT NULL,
    FOREIGN KEY (unit_id) REFERENCES units(id)
);

CREATE TABLE assets (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    asset_number TEXT NOT NULL UNIQUE,
    asset_name TEXT NOT NULL,
    user_id INTEGER  NOT NULL, 
    creation_date TIMESTAMP NOT NULL,
    value REAL NOT NULL,
    unit_id INTEGER NOT NULL,
    FOREIGN KEY (unit_id) REFERENCES units(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);


INSERT INTO units (name, creation_date, closed_date)
VALUES
('IT Department', '2008-08-01 00:00:00', null),
('Administration', '2008-08-01 00:00:00', null),
('Office Management', '2008-08-01 00:00:00', null),
('Training Department', '2008-08-01 00:00:00', null),
('Customer Service', '2009-04-01 00:00:00', null),
('Human Resources', '2007-08-01 00:00:00', null),
('Sales Department', '2010-01-01 00:00:00', null),
('Cafeteria', '2008-08-01 00:00:00', null);

INSERT INTO users (name, hire_date, is_active, is_admin, resignation_date, password, unit_id)
VALUES 
('John', '2020-01-01 00:00:00', 1, 0, NULL, 'password1', 1),
('Alice', '2020-02-01 00:00:00', 1, 1, NULL, 'password2', 2),
('Bob', '2020-03-01 00:00:00', 1, 0, NULL, 'password3', 3),
('Emily', '2020-04-01 00:00:00', 1, 0, NULL, 'password4', 3),
('Michael', '2020-05-01 00:00:00', 1, 0, NULL, 'password5', 4),
('Sarah', '2020-06-01 00:00:00', 1, 0, NULL, 'password6', 4),
('David', '2020-07-01 00:00:00', 1, 0, NULL, 'password7', 5),
('Karen', '2020-08-01 00:00:00', 1, 0, NULL, 'password8', 2),
('Jessica', '2020-09-01 00:00:00', 1, 0, NULL, 'password9', 1),
('James', '2020-10-01 00:00:00', 1, 0, NULL, 'password10', 6),
('Emma', '2020-11-01 00:00:00', 1, 0, NULL, 'password11', 2),
('Andrew', '2020-12-01 00:00:00', 1, 0, NULL, 'password12', 7),
('Olivia', '2021-01-01 00:00:00', 1, 0, NULL, 'password13', 2),
('Daniel', '2021-02-01 00:00:00', 1, 0, NULL, 'password14', 2),
('Sophia', '2021-03-01 00:00:00', 1, 0, NULL, 'password15', 8),
('Ethan', '2021-04-01 00:00:00', 1, 0, NULL, 'password16', 8),
('Isabella', '2021-05-01 00:00:00', 1, 0, NULL, 'password17', 8),
('Mia', '2021-06-01 00:00:00', 1, 0, NULL, 'password18', 2),
('Noah', '2021-07-01 00:00:00', 1, 0, NULL, 'password19', 3),
('Ava', '2021-08-01 00:00:00', 1, 0, NULL, 'password20', 3);

INSERT INTO assets (asset_number, asset_name, user_id, creation_date, value, unit_id) VALUES 
('A001', 'Computer', 1, '2024-03-18 00:00:00', 1500.00, 1),
('A002', 'Printer', 2, '2024-03-18 00:00:00', 800.00, 2),
('A003', 'Desk', 3, '2024-03-17 00:00:00', 200.00, 3),
('A004', 'Chair', 4, '2024-03-17 00:00:00', 100.00, 3),
('A005', 'Projector', 5, '2024-03-16 00:00:00', 1200.00, 4),
('A006', 'Whiteboard', 6, '2024-03-16 00:00:00', 150.00, 4),
('A007', 'Telephone', 7, '2024-03-15 00:00:00', 100.00, 5),
('A008', 'Filing Cabinet', 8, '2024-03-15 00:00:00', 300.00, 2),
('A009', 'Scanner', 9, '2024-03-14 00:00:00', 400.00, 1),
('A010', 'Office Chair', 10, '2024-03-14 00:00:00', 250.00, 6),
('A011', 'Meeting Table', 11, '2024-03-13 00:00:00', 600.00, 2),
('A012', 'Laptop', 12, '2024-03-13 00:00:00', 1200.00, 7),
('A013', 'Conference Phone', 13, '2024-03-12 00:00:00', 500.00, 2),
('A014', 'Water Dispenser', 14, '2024-03-12 00:00:00', 150.00, 2),
('A015', 'Refrigerator', 15, '2024-03-11 00:00:00', 800.00, 8),
('A016', 'Microwave', 16, '2024-03-11 00:00:00', 100.00, 8),
('A017', 'Coffee Machine', 17, '2024-03-10 00:00:00', 200.00, 8),
('A018', 'Shredder', 18, '2024-03-10 00:00:00', 150.00, 2),
('A019', 'Air Purifier', 19, '2024-03-09 00:00:00', 300.00, 3),
('A020', 'Desk Lamp', 20, '2024-03-09 00:00:00', 50.00, 3);