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
public class Project extends BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private String name;

    @ManyToMany
    @JoinColumn(name="parent_project_id")
    private List<Project> basicProject;
    @ManyToMany(mappedBy = "basicProject")
    private List<Project> projects;

    @ManyToMany(mappedBy = "projects")
    private List<Document> documents;

    @Embedded
    private Approvals approvals;
}