DROP TABLE IF EXISTS room;

CREATE TABLE room (
  number INT PRIMARY KEY,
  floor INT NOT NULL,
  price INT NOT NULL,
  total_size DOUBLE NOT NULL,
  is_booked BIT NOT NULL,
  booked_from DATE,
  booked_until DATE
);

INSERT INTO room (number, floor, price, total_size, is_booked, booked_from, booked_until) VALUES
  (101, 1, 180, 12.0, FALSE, NULL, NULL);