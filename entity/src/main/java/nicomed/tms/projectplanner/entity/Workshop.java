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
@Table(name = "WORKSHOP")
public class Workshop extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "INDEX")
    private int index;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @OneToMany(mappedBy = "workshop")
    private List<TechnicalTask> technicalTasks;

    @OneToMany(mappedBy = "workshop")
    private List<Memo> memos;

    @OneToMany(mappedBy = "workshop")
    private List<Project> projects;
}