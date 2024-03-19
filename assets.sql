.open mli-asset-management.db


CREATE TABLE assets (
    id int PRIMARY KEY AUTOINCREMENT,
    asset_number TEXT NOT NULL,
    asset_name TEXT NOT NULL,
    unit_of_use TEXT NOT NULL,
    user TEXT NOT NULL,
    creation_date DATE NOT NULL,
    value REAL NOT NULL
);

INSERT INTO assets (asset_number, asset_name, unit_of_use, user, creation_date, value) VALUES 
('A001', 'Computer', 'IT Department', 'John Doe', '2024-03-18', 1500.00),
('A002', 'Printer', 'Administration', 'Alice Smith', '2024-03-18', 800.00),
('A003', 'Desk', 'Office Management', 'Bob Johnson', '2024-03-17', 200.00),
('A004', 'Chair', 'Office Management', 'Emily Brown', '2024-03-17', 100.00),
('A005', 'Projector', 'Training Department', 'Michael Wilson', '2024-03-16', 1200.00),
('A006', 'Whiteboard', 'Training Department', 'Sarah Lee', '2024-03-16', 150.00),
('A007', 'Telephone', 'Customer Service', 'David Martinez', '2024-03-15', 100.00),
('A008', 'Filing Cabinet', 'Administration', 'Karen Taylor', '2024-03-15', 300.00),
('A009', 'Scanner', 'IT Department', 'Jessica Garcia', '2024-03-14', 400.00),
('A010', 'Office Chair', 'Human Resources', 'James Brown', '2024-03-14', 250.00),
('A011', 'Meeting Table', 'Administration', 'Emma Rodriguez', '2024-03-13', 600.00),
('A012', 'Laptop', 'Sales Department', 'Andrew Wilson', '2024-03-13', 1200.00),
('A013', 'Conference Phone', 'Administration', 'Olivia Hernandez', '2024-03-12', 500.00),
('A014', 'Water Dispenser', 'Administration', 'Daniel Martinez', '2024-03-12', 150.00),
('A015', 'Refrigerator', 'Cafeteria', 'Sophia Johnson', '2024-03-11', 800.00),
('A016', 'Microwave', 'Cafeteria', 'Ethan Lee', '2024-03-11', 100.00),
('A017', 'Coffee Machine', 'Cafeteria', 'Isabella Brown', '2024-03-10', 200.00),
('A018', 'Shredder', 'Administration', 'Mia Garcia', '2024-03-10', 150.00),
('A019', 'Air Purifier', 'Office Management', 'Noah Wilson', '2024-03-09', 300.00),
('A020', 'Desk Lamp', 'Office Management', 'Ava Martinez', '2024-03-09', 50.00);