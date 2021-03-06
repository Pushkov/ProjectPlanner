package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dateTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "TITLE_LIST_ID")
    private TitleList titleList;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "WORKSHOP_ID")
    private Workshop workshop;

    @OneToMany(mappedBy = "memo")
    private List<Project> projects;

}