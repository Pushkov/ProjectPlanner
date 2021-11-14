package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TITLE_LIST")
public class TitleList extends BaseEntity<Integer> {
    @Id
    private Integer id;

    @OneToMany(mappedBy = "titleList")
    private List<TechnicalTask> technicalTasks;

    @OneToMany(mappedBy = "titleList")
    private List<Memo> memos;
}