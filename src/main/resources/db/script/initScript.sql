DROP SCHEMA IF EXISTS clinic CASCADE;

CREATE SCHEMA clinic;

CREATE TABLE doctor
(
    id             SERIAL       NOT NULL,
    firstName      varchar(50)  NOT NULL,
    middleName     varchar(50)  NOT NULL,
    lastName       varchar(50)  NOT NULL,
    specialization varchar(100) NOT NULL,
    district       varchar(100) NOT NULL,
    cabinet        varchar(100) NOT NULL,
    createdAt      TIMESTAMP    NOT NULL,
    updatedAt      TIMESTAMP    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE doctor_schedule
(
    id                  SERIAL    NOT NULL,
    createdAt           TIMESTAMP NOT NULL,
    updatedAt           TIMESTAMP NOT NULL,
    date                DATE      NOT NULL,
    doctorId            INT       NOT NULL,
    startWork           TIME      NOT NULL,
    endWork             TIME      NOT NULL,
    maxAppointmentCount INT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE appointment
(
    id               SERIAL      NOT NULL,
    createdAt        TIMESTAMP   NOT NULL,
    updatedAt        TIMESTAMP   NOT NULL,
    doctorScheduleId INT         NOT NULL,
    startTime        TIME        NOT NULL,
    endTime          TIME        NOT NULL,
    firstName        varchar(50) NOT NULL,
    middleName       varchar(50) NOT NULL,
    lastName         varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE doctor_schedule
    ADD CONSTRAINT doctorSchedule_fk0 FOREIGN KEY (doctorId) REFERENCES doctor (id);

ALTER TABLE appointment
    ADD CONSTRAINT appointment_fk0 FOREIGN KEY (doctorScheduleId) REFERENCES doctor_schedule (id);
