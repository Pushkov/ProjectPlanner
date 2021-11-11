package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@
        AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class TechnicalTask extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String name;
    @Column(name = "date_time")
    private OffsetDateTime dateTime;

    @ManyToOne
    private TitleList titleList;
    @ManyToOne
    private Workshop workshop;

    @OneToMany(mappedBy = "baseTask")
    private List<TechnicalTask> extensions;
    @ManyToOne
    @JoinColumn(name = "base_task_id")
    private TechnicalTask baseTask;

    @OneToMany(mappedBy = "task")
    private List<Project> projects;
}