--The datbase name, user and password need to be edited in the database.properties file. The jdbc drivers used are also attached in the zip

-- Create the database named Satoshi
CREATE DATABASE Satoshi;

-- Use the Satoshi database
USE Satoshi;

--Creating Car table

CREATE TABLE Car (
    car_id VARCHAR(50) PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(50) NOT NULL,
    no_of_passengers INT NOT NULL,
    fuel_type VARCHAR(20) NOT NULL CHECK (fuel_type IN ('Petrol', 'Diesel')),
    purchase_date DATE NOT NULL,
    transmission_type VARCHAR(20) NOT NULL CHECK (transmission_type IN ('Automatic', 'Manual')),
    is_available BIT NOT NULL DEFAULT 1,
    car_price DOUBLE PRECISION NOT NULL,
    rental_price DOUBLE PRECISION NOT NULL,
    is_removed BIT NOT NULL DEFAULT 0,
    last_oil_change DATE,
    next_oil_change DATE,
    last_inspection_date DATE,
    next_inspection_date DATE,
    car_status VARCHAR(20) NOT NULL CHECK (car_status IN ('Functional', 'OutOfService')),
    last_washing_date DATE,
    next_washing_date DATE
);

CREATE SEQUENCE car_seq
    START WITH 1
    INCREMENT BY 1;

ALTER TABLE Car
ADD CONSTRAINT DF_Car_car_id DEFAULT ('C' + RIGHT('0000' + CAST(NEXT VALUE FOR car_seq AS VARCHAR(5)), 4)) FOR car_id;



INSERT INTO Car (brand, model, color, no_of_passengers, fuel_type, purchase_date, transmission_type, car_price, rental_price, car_status, is_available, is_removed)
VALUES
('Toyota', 'Camry', 'Black', 5, 'PETROL', '2023-01-01', 'AUTOMATIC', 25000.00, 50.00, 'FUNCTIONAL', 1, 0),
('Honda', 'Accord', 'White', 5, 'PETROL', '2022-06-15', 'AUTOMATIC', 27000.00, 55.00, 'FUNCTIONAL', 1, 0),
('Ford', 'Fusion', 'Silver', 5, 'DIESEL', '2021-12-20', 'MANUAL', 23000.00, 45.00, 'FUNCTIONAL', 1, 0);

select * from Car

----Inserting customer

CREATE TABLE Customer (
    customer_id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contact_no BIGINT NOT NULL,
    is_removed BIT NOT NULL DEFAULT 0,
    password VARCHAR(255) NOT NULL
);

CREATE SEQUENCE customer_seq
    START WITH 1
    INCREMENT BY 1;

ALTER TABLE Customer
ADD CONSTRAINT DF_Customer_customer_id DEFAULT ('CUS' + RIGHT('0000' + CAST(NEXT VALUE FOR customer_seq AS VARCHAR(5)), 4)) FOR customer_id;


INSERT INTO Customer (first_name, last_name, address, email, contact_no, is_removed, password)
VALUES
('John', 'Doe', '123 Main St, City, Country', 'customer1@example.com', 1234567890, 0, 'mBCMBKTrOa8VEUTGvM+LW4j73FVWupbNexYFd031aMCxTXbs7fIggVHIM7VzNjdO'),
('Jane', 'Smith', '456 Elm St, City, Country', 'customer2@example.com', 9876543210, 0, 'xS9Zb+NC9PboXyEV1VPn9roWyj5BFRy5PqEAAhqqiiHkaRryWxluzsJwNUcLwlOy'),
('Alice', 'Johnson', '789 Oak St, City, Country', 'customer3@example.com', 1357924680, 1, '0CycuCHTMXj2j5oXIwV724UuwtKTphXfwjm+aTppcINRgJlzvpmTf+1AZCpfA+Zl');

--Inserting employees

CREATE TABLE Employee (
    employee_id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number BIGINT NOT NULL,
    date_of_birth DATE NOT NULL,
    hire_date DATE NOT NULL,
    is_removed BIT NOT NULL DEFAULT 0,
    is_manager BIT NOT NULL DEFAULT 0,
    password VARCHAR(255) NOT NULL
);

CREATE SEQUENCE employee_seq
    START WITH 1
    INCREMENT BY 1;

ALTER TABLE Employee
ADD CONSTRAINT DF_Employee_employee_id DEFAULT ('E' + RIGHT('0000' + CAST(NEXT VALUE FOR employee_seq AS VARCHAR(5)), 4)) FOR employee_id;

INSERT INTO Employee (first_name, last_name, address, email, phone_number, date_of_birth, hire_date, is_removed, is_manager, password)
VALUES
('John', 'Doe', '123 Main St, City, Country', 'employee1@example.com', 1234567890, '1990-01-01', '2020-01-01', 0, 0, 'aDQYMGhz4+twZIsUESOx+b2vS6Xqp7YAj1Ltb70S69H0dZWh7tQInFmj4Geena65'),
('Jane', 'Smith', '456 Elm St, City, Country', 'employee2@example.com', 9876543210, '1995-05-15', '2021-03-15', 0, 1, 'pFjjyd3khpDoTZk8lZ7Hv+6CwxsHFGR8ULZuHO7Fl0lGOopCp0MCg+RapoUchYp0'),
('Alice', 'Johnson', '789 Oak St, City, Country', 'employee3@example.com', 1357924680, '1985-11-30', '2019-07-10', 0, 0, '0uOtsMxBofxqfYbGTN5aV/DeIkmmboblV8kijcVei6LWqga69sbhSmGnbmnXJxfL');

select * from Customer
select * from Employee

CREATE TABLE booking (
    booking_id VARCHAR(10) PRIMARY KEY,
    car_id VARCHAR(50) NOT NULL,
    customer_id VARCHAR(50) NOT NULL,
    employee_id VARCHAR(50) NOT NULL,
    booking_date DATE NOT NULL,
    return_date DATE,
    cancellation_date DATE,
    FOREIGN KEY (car_id) REFERENCES Car(car_id),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

CREATE SEQUENCE booking_seq
    START WITH 1
    INCREMENT BY 1;

ALTER TABLE booking
ADD CONSTRAINT DF_booking_booking_id DEFAULT ('B' + RIGHT('0000' + CAST(NEXT VALUE FOR booking_seq AS VARCHAR(5)), 4)) FOR booking_id;


INSERT INTO booking (car_id, customer_id, employee_id, booking_date, return_date, cancellation_date)
VALUES
('C0002', 'CUS0002', 'E0002', '2024-03-21', '2024-03-25', NULL);

select * from Booking