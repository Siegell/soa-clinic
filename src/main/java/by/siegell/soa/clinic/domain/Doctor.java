package by.siegell.soa.clinic.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ToString(callSuper = true)
public class Doctor extends Entity {
    private String firstName;
    private String middleName;
    private String lastName;

    private String specialization;
    private String district;
    private String cabinet;

    public boolean isSpecialist() {
        return StringUtils.isNoneEmpty(district);
    }
}
