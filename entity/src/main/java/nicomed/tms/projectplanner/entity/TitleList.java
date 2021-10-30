package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Year;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class TitleList extends BaseEntity<Year> {
    @Id
    private Year id;

    @OneToMany(mappedBy = "titleList")
    private List<TechnicalTask> technicalTasks;

    @OneToMany(mappedBy = "titleList")
    private List<Memo> memos;
}