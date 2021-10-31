package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PlanPK implements Serializable {

    private int year;
    private int month;
    @ManyToOne
    private Department department;
}
