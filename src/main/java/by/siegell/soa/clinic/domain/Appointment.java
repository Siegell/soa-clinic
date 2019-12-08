package by.siegell.soa.clinic.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    protected Long id;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;
    private Long doctorScheduleId;
    private LocalTime startTime;
    private LocalTime endTime;

    private String firstName;
    private String middleName;
    private String lastName;

    public Duration getAppointmentDuration() {
        return Duration.between(startTime, endTime);
    }

    public boolean isOpenAppointment() {
        return StringUtils.isNoneEmpty(firstName) && StringUtils.isNoneEmpty(middleName) && StringUtils.isNoneEmpty(lastName);
    }

    public boolean isEmpty() {
        return getId() == null && getCreatedAt() == null && getUpdatedAt() == null;
    }

}
