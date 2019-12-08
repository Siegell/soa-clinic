package by.siegell.soa.clinic.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    protected Long id;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;
    private String firstName;
    private String middleName;
    private String lastName;

    private String specialization;
    private String district;
    private String cabinet;

    public boolean isSpecialist() {
        return StringUtils.isEmpty(district);
    }

    public boolean isEmpty() {
        return getId() == null && getCreatedAt() == null && getUpdatedAt() == null;
    }
}
