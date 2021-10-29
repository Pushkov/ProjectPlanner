package nicomed.tms.projectplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Department extends BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String fullName;

    @ManyToOne
    private Department basicDepartment;
    @OneToMany
    private List<Department> departments;
    @OneToMany
    private List<Engineer> engineers;
//    @OneToMany
//    private List<Plan> plans;
//    @OneToMany
//    private List<Workshop> workshops;

}