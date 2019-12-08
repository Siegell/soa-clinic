package by.siegell.soa.clinic.domain;

import lombok.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSchedule {
    protected Long id;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;
    private LocalDate date;
    private Long doctorId;

    private LocalTime startWork;
    private LocalTime endWork;

    private Integer maxAppointmentCount;

    public Duration getWorkTime() {
        return Duration.between(startWork, endWork);
    }

    public boolean isEmpty() {
        return getId() == null && getCreatedAt() == null && getUpdatedAt() == null;
    }

}
