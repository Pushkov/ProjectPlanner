package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Memo extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Column(name = "date_time")
    private OffsetDateTime dateTime;

    @ManyToOne
    private TitleList titleList;
    @ManyToOne
    private Workshop workshop;

    @OneToMany(mappedBy = "memo")
    private List<Project> projects;

}