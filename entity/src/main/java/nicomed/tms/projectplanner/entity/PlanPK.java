package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
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

    @Column(name = "YEAR")
    private int year;
    @Column(name = "MONTH")
    private int month;
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
