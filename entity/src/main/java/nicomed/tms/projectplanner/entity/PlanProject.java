package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
//@Setter
@Entity
@Table(name = "PLAN_PROJECT")
public class PlanProject {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "MONTH")
    private Integer month;
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    @Column(name = "PROJECT_DESIGNATION")
    private String projectDesignation;
    @Column(name = "PROJECT_NAME")
    private String projectName;
    @Column(name = "TASK_NAME")
    private String taskName;
}
