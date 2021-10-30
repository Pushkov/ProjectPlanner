package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.Month;
import java.time.Year;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PlanPK implements Serializable {

    private Year year;
    private Month month;
    @ManyToOne
    private Department department;
}
