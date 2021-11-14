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
@Table(name = "TECHNICAL_TASK")
public class TechnicalTask extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DATE_TIME")
    private OffsetDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "TITLE_LIST_ID")
    private TitleList titleList;
    @ManyToOne
    @JoinColumn(name = "WORKSHOP_ID")
    private Workshop workshop;

    @OneToMany(mappedBy = "baseTask")
    private List<TechnicalTask> extensions;
    @ManyToOne
    @JoinColumn(name = "BASE_TASK_ID")
    private TechnicalTask baseTask;

    @OneToMany(mappedBy = "task")
    private List<Project> projects;
}