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
public class Workshop extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String fullName;
    private int index;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "workshop")
    private List<TechnicalTask> technicalTasks;

    @OneToMany(mappedBy = "workshop")
    private List<Memo> memos;

    @OneToMany(mappedBy = "workshop")
    private List<Project> projects;
}