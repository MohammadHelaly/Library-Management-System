CREATE DATABASE IF NOT EXISTS `library_management_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE IF NOT EXISTS library_management_system.admin (
id INT AUTO_INCREMENT, 
name TEXT NOT NULL,
email VARCHAR(255) NOT NULL, 
password VARCHAR(255) NOT NULL,
branch INT NOT NULL,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS library_management_system.librarian (
id INT AUTO_INCREMENT, 
name TEXT NOT NULL,
email VARCHAR(255) NOT NULL, 
password VARCHAR(255) NOT NULL,
phone INT NOT NULL,
branch INT NOT NULL,
salary FLOAT NOT NULL,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS library_management_system.customer (
id INT AUTO_INCREMENT, 
name TEXT NOT NULL,
email VARCHAR(255) NOT NULL, 
password VARCHAR(255) NOT NULL,
phone INT NOT NULL,
city TEXT NOT NULL, 
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS library_management_system.branch (
id INT AUTO_INCREMENT,
address VARCHAR(255) NOT NULL,   
city TEXT NOT NULL,
phone INT NOT NULL,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS library_management_system.book (
call_num INT AUTO_INCREMENT, 
title VARCHAR(255) NOT NULL,
author TEXT NOT NULL, 
genre VARCHAR(255) NOT NULL,
total_quantity INT NOT NULL,
total_stock INT NOT NULL,
fee float NOT NULL, 
PRIMARY KEY (call_num));

CREATE TABLE IF NOT EXISTS library_management_system.book_inventory (
book_call_num INT,
branch_id INT,
branch_quantity INT NOT NULL,
branch_stock INT NOT NULL,
PRIMARY KEY (book_call_num,branch_id));

ALTER TABLE library_management_system.book_inventory
ADD CONSTRAINT fk_book_inventory_branch
FOREIGN KEY (branch_id)
REFERENCES library_management_system.branch (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE library_management_system.book_inventory
ADD CONSTRAINT fk_book_inventory_book
FOREIGN KEY (book_call_num)
REFERENCES library_management_system.book (call_num)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE library_management_system.admin
ADD CONSTRAINT fk_admin_branch
FOREIGN KEY (branch)
REFERENCES library_management_system.branch (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE library_management_system.librarian
ADD CONSTRAINT fk_librarian_branch
FOREIGN KEY (branch)
REFERENCES library_management_system.branch (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE checkout_item
ADD COLUMN payment_due FLOAT NOT NULL;

ALTER TABLE payment
ADD COLUMN payment_date DATE NOT NULL;

ALTER TABLE admin
ADD COLUMN phone FLOAT NOT NULL AFTER password;

INSERT INTO library_management_system.admin (name,email,password,phone,branch,salary) VALUES 
('admin2','admin2@admin.com',md5('admin2'),'123456789','2','5000'); 

INSERT INTO library_management_system.librarian (name,email,password,phone,branch) VALUES 
('lib1','lib1@lib.com',md5('lib1'),00000000,1);

INSERT INTO library_management_system.librarian (name,email,password,phone,branch) VALUES 
('lib2','lib2@lib.com',md5('lib2'),00000000,2);

INSERT INTO library_management_system.branch (address,city) VALUES 
('address1','city1');

INSERT INTO library_management_system.branch (address,city) VALUES 
('address2','city2');

INSERT INTO book (title, author, genre, total_quantity, total_stock, fee)
VALUES ('Book1', 'Author1', 'Genre1', 5, 3, 7.99),
       ('Book2', 'Author2', 'Genre2', 10, 5, 9.99);

INSERT INTO checkout (customer_id, branch_id, checkout_date, status, pickup_date,return_date, total_payment_due, fine)
VALUES ('1', '2', '2023-06-16', 'Pending', '2023-06-16','2023-06-20','7.99','0.0'); 

INSERT INTO checkout_item (checkout_id, book_call_num, quantity, payment_due)
VALUES ('2', '2','1','7.99');     
       