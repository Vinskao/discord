.open mli-asset-management.db

DROP TABLE assets;
DROP TABLE users;
CREATE TABLE assets (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    asset_number TEXT NOT NULL,
    asset_name TEXT NOT NULL,
    unit_of_use TEXT NOT NULL,
    user TEXT NOT NULL, 
    creation_date DATE NOT NULL,
    value REAL NOT NULL
);

INSERT INTO assets (asset_number, asset_name, unit_of_use, user, creation_date, value) VALUES 
('A001', 'Computer', 'IT Department', 'John', '2024-03-18', 1500.00),
('A002', 'Printer', 'Administration', 'Alice', '2024-03-18', 800.00),
('A003', 'Desk', 'Office Management', 'Bob', '2024-03-17', 200.00),
('A004', 'Chair', 'Office Management', 'Emily', '2024-03-17', 100.00),
('A005', 'Projector', 'Training Department', 'Michael', '2024-03-16', 1200.00),
('A006', 'Whiteboard', 'Training Department', 'Sarah', '2024-03-16', 150.00),
('A007', 'Telephone', 'Customer Service', 'David', '2024-03-15', 100.00),
('A008', 'Filing Cabinet', 'Administration', 'Karen', '2024-03-15', 300.00),
('A009', 'Scanner', 'IT Department', 'Jessica', '2024-03-14', 400.00),
('A010', 'Office Chair', 'Human Resources', 'James', '2024-03-14', 250.00),
('A011', 'Meeting Table', 'Administration', 'Emma', '2024-03-13', 600.00),
('A012', 'Laptop', 'Sales Department', 'Andrew', '2024-03-13', 1200.00),
('A013', 'Conference Phone', 'Administration', 'Olivia', '2024-03-12', 500.00),
('A014', 'Water Dispenser', 'Administration', 'Daniel', '2024-03-12', 150.00),
('A015', 'Refrigerator', 'Cafeteria', 'Sophia', '2024-03-11', 800.00),
('A016', 'Microwave', 'Cafeteria', 'Ethan', '2024-03-11', 100.00),
('A017', 'Coffee Machine', 'Cafeteria', 'Isabella', '2024-03-10', 200.00),
('A018', 'Shredder', 'Administration', 'Mia', '2024-03-10', 150.00),
('A019', 'Air Purifier', 'Office Management', 'Noah', '2024-03-09', 300.00),
('A020', 'Desk Lamp', 'Office Management', 'Ava', '2024-03-09', 50.00);


CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL, 
    hire_date DATE NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT 1,
    password TEXT NOT NULL
);

INSERT INTO users (name, hire_date, is_active, password) VALUES 
('John', '2020-01-01', 1, 'password1'),
('Alice', '2020-02-01', 1, 'password2'),
('Bob', '2020-03-01', 1, 'password3'),
('Emily', '2020-04-01', 1, 'password4'),
('Michael', '2020-05-01', 1, 'password5'),
('Sarah', '2020-06-01', 1, 'password6'),
('David', '2020-07-01', 1, 'password7'),
('Karen', '2020-08-01', 1, 'password8'),
('Jessica', '2020-09-01', 1, 'password9'),
('James', '2020-10-01', 1, 'password10'),
('Emma', '2020-11-01', 1, 'password11'),
('Andrew', '2020-12-01', 1, 'password12'),
('Olivia', '2021-01-01', 1, 'password13'),
('Daniel', '2021-02-01', 1, 'password14'),
('Sophia', '2021-03-01', 1, 'password15'),
('Ethan', '2021-04-01', 1, 'password16'),
('Isabella', '2021-05-01', 1, 'password17'),
('Mia', '2021-06-01', 1, 'password18'),
('Noah', '2021-07-01', 1, 'password19'),
('Ava', '2021-08-01', 1, 'password20');