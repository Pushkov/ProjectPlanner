package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Department extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FULL_NAME")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "BASIC_DEPARTMENT_ID")
    private Department basicDepartment;
    @OneToMany(mappedBy = "basicDepartment")
    private List<Department> departments;

    @OneToMany(mappedBy = "department")
    private List<Engineer> engineers;
    @OneToMany(mappedBy = "department")
    private List<Project> projects;
    @OneToMany(mappedBy = "department")
    private List<Workshop> workshops;

}