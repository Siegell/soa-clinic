USE `clinic`;

CREATE TABLE `Doctor`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `firstName`      varchar(50)  NOT NULL,
    `middleName`     varchar(50)  NOT NULL,
    `lastName`       varchar(50)  NOT NULL,
    `specialization` varchar(100) NOT NULL,
    `district`       varchar(100) NOT NULL,
    `cabinet`        varchar(100) NOT NULL,
    `createdAt`      TIMESTAMP    NOT NULL,
    `updatedAt`      TIMESTAMP    NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `DoctorSchedule`
(
    `id`                  INT       NOT NULL AUTO_INCREMENT,
    `createdAt`           TIMESTAMP NOT NULL,
    `updatedAt`           TIMESTAMP NOT NULL,
    `date`                DATE      NOT NULL,
    `doctorId`            INT       NOT NULL,
    `startWork`           TIME      NOT NULL,
    `endWork`             TIME      NOT NULL,
    `maxAppointmentCount` INT       NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Appointment`
(
    `id`               INT         NOT NULL AUTO_INCREMENT,
    `createdAt`        TIMESTAMP   NOT NULL,
    `updatedAt`        TIMESTAMP   NOT NULL,
    `doctorScheduleId` INT         NOT NULL,
    `startTime`        TIME        NOT NULL,
    `endTime`          TIME        NOT NULL,
    `firstName`        varchar(50) NOT NULL,
    `middleName`       varchar(50) NOT NULL,
    `lastName`         varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `DoctorSchedule`
    ADD CONSTRAINT `DoctorSchedule_fk0` FOREIGN KEY (`doctorId`) REFERENCES `Doctor` (`id`);

ALTER TABLE `Appointment`
    ADD CONSTRAINT `Appointment_fk0` FOREIGN KEY (`doctorScheduleId`) REFERENCES `DoctorSchedule` (`id`);
