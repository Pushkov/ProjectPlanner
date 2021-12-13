package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "PLAN_POINT")
public class PlanPoint extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDER_IN_PLAN")
    private Integer orderInPlan;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PLAN_YEAR", referencedColumnName = "YEAR"),
            @JoinColumn(name = "PLAN_MONTH", referencedColumnName = "MONTH"),
            @JoinColumn(name = "PLAN_DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    })
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "DESIGNER_ID")
    private Engineer designer;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "READINESS")
    private Integer readiness;

}