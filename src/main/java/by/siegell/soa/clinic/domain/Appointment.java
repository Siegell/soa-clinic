package by.siegell.soa.clinic.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ToString(callSuper = true)
public class Appointment extends Entity {
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
}
