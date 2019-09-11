package by.siegell.soa.clinic.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DoctorSchedule extends Entity {
    private LocalDate date;
    private Long doctorId;

    private LocalTime startWork;
    private LocalTime endWork;

    private Integer maxAppointmentCount;

    public Duration getWorkTime() {
        return Duration.between(startWork, endWork);
    }
}
