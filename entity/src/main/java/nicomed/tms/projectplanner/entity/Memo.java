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
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "DATE_TIME")
    private OffsetDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "TITLE_LIST_ID")
    private TitleList titleList;
    @ManyToOne
    @JoinColumn(name = "WORKSHOP_ID")
    private Workshop workshop;

    @OneToMany(mappedBy = "memo")
    private List<Project> projects;

}