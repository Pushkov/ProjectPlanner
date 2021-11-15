package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PLAN_YEAR", referencedColumnName = "YEAR"),
            @JoinColumn(name = "PLAN_MONTH", referencedColumnName = "MONTH"),
            @JoinColumn(name = "PLAN_DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    })
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

}