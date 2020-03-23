DROP TABLE IF EXISTS room;

CREATE TABLE room (
  number INT PRIMARY KEY,
  floor INT NOT NULL,
  type VARCHAR NOT NULL,
  price INT NOT NULL,
  total_size DOUBLE NOT NULL,
  is_booked BOOLEAN NOT NULL,
  booked_from DATE,
  booked_until DATE,
  room_properties VARCHAR NOT NULL
);

INSERT INTO room (number, floor, type, price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (101, 1, 'ONEBEDROOM', 180, 12.0, FALSE, NULL, NULL, 'BALCONY,TV');
INSERT INTO room (number, floor, type, price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (102, 1, 'ONEBEDROOM', 180, 12.0, FALSE, NULL, NULL, 'BALCONY,TV');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (103, 1, 'ONEBEDROOM', 180, 12.0, FALSE, NULL, NULL, 'BALCONY,TV');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (104, 1, 'ONEBEDROOM', 180, 12.0, FALSE, NULL, NULL, 'BALCONY,TV');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (105, 1, 'ONEBEDROOM', 180, 12.0, FALSE, NULL, NULL, 'BALCONY,TV');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (201, 2, 'STANDARD', 280, 20.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (202, 2, 'STANDARD', 280, 20.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (203, 2, 'STANDARD', 280, 20.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (204, 2, 'STANDARD', 280, 20.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (205, 2, 'STANDARD', 280, 20.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (301, 3, 'PENTHOUSE', 550, 50.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR,MINIBAR,JACUZZI');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (302, 3, 'PENTHOUSE', 550, 50.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR,MINIBAR,JACUZZI');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (303, 3, 'PENTHOUSE', 550, 50.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR,MINIBAR,JACUZZI');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (304, 3, 'PENTHOUSE', 550, 50.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR,MINIBAR,JACUZZI');
INSERT INTO room (number, floor, type,  price, total_size, is_booked, booked_from, booked_until, room_properties)
VALUES (305, 3, 'PENTHOUSE', 550, 50.0, FALSE, NULL, NULL, 'BALCONY,TV,REFRIGERATOR,MINIBAR,JACUZZI');