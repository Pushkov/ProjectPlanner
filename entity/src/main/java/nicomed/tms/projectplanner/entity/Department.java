package nicomed.tms.projectplanner.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Department extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "basic_department_id")
    private Department basicDepartment;
    @OneToMany(mappedBy = "basicDepartment")
    @JsonBackReference
    private List<Department> departments;

//    @OneToMany(mappedBy = "department")
//    private List<Engineer> engineers;
//    @OneToMany(mappedBy = "department")
//    private List<Project> projects;
//    @OneToMany(mappedBy = "department")
//    private List<Workshop> workshops;

}