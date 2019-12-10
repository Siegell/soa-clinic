INSERT INTO doctor (lastname, firstname, middlename, specialization, district, cabinet, createdat, updatedat)
VALUES ('Гребенщиков', 'Егор', 'Филиппович', 'District', '15Б', '105А', '2019-09-28 19:10:25-07',
        '2016-06-22 19:10:25-07'),
       ('Жиренков', 'Илья', 'Федосиевич', 'Surgeon', '', '654', '2019-09-28 19:10:25-07',
        '2016-06-22 19:10:25-07'),
       ('Косма', 'Моисей', 'Назарович', 'Dentist', '', '428', '2019-09-28 19:10:25-07',
        '2016-06-22 19:10:25-07');

INSERT INTO doctor_schedule (date, doctorId, startWork, endWork, maxAppointmentCount, createdAt, updatedAt)
VALUES ('2019-09-29', 1, '08:00:00-00', '17:00:00-00', 5, '2019-09-28 19:10:25-07', '2019-09-28 19:10:25-07'),
       ('2019-09-29', 2, '08:00:00-00', '17:00:00-00', 5, '2019-09-28 19:10:25-07', '2019-09-28 19:10:25-07'),
       ('2019-09-29', 3, '08:00:00-00', '17:00:00-00', 5, '2019-09-28 19:10:25-07', '2019-09-28 19:10:25-07');

INSERT INTO appointment (doctorscheduleid, starttime, endtime, lastname, firstname, middlename, createdat, updatedat)
VALUES (1, '08:05:00-07', '08:15:00-07', 'Шалаганова', 'Аза', 'Геннадиевна', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07'),
       (1, '08:15:00-07', '08:25:00-07', 'Богун', 'Ипполит', 'Адамович', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07'),
       (1, '08:25:00-07', '08:35:00-07', 'Гурин', 'Аскольд', 'Андриянович', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07'),
       (2, '08:05:00-07', '08:15:00-07', 'Радостина', 'Антонина', 'Кузьмевна', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07'),
       (2, '08:05:00-07', '08:25:00-07', 'Королёва', 'Жанна', 'Павеловна', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07'),
       (2, '08:15:00-07', '08:35:00-07', 'Азаренков', 'Макар', 'Евстафиевич', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07'),
       (3, '08:25:00-07', '08:15:00-07', 'Ошурков', 'Ярослав', 'Зиновиевич', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07'),
       (3, '08:15:00-07', '08:25:00-07', 'Бибиков', 'Алексей', 'Фомевич', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07'),
       (3, '08:25:00-07', '08:35:00-07', 'Негин', 'Игорь', 'Касьянович', '2019-09-28 19:10:25-07',
        '2019-09-28 19:10:25-07');

INSERT INTO "user" (username, password, roles)
VALUES ('admin', 'admin', 'admin'),
       ('nurse', 'nurse', 'nurse');