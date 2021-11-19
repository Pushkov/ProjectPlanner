package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "PROJECT")
public class Project extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "PROJECT_BASIC_PROJECT",
            joinColumns = @JoinColumn(name = "PROJECTS_ID"),
            inverseJoinColumns = @JoinColumn(name = "BASIC_PROJECT_ID"))
    private List<Project> basicProject;
    @ManyToMany(mappedBy = "basicProject")
    private List<Project> projects;

    @OneToMany(mappedBy = "project")
    private List<PlanPoint> planPoints;

    @ManyToMany(mappedBy = "projects")
    private List<DocumentSigned> documents;

    @ManyToOne
    private Department department;
    @ManyToOne
    private Workshop workshop;

    @Embedded
    private ProjectApprovals projectApprovals;

    @ManyToOne
    private TechnicalTask task;
    @ManyToOne
    private Memo memo;
}