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
@Table(name = "TITLE_LIST")
public class TitleList extends BaseEntity<Integer> {
    @Id
    private Integer id;

    @OneToMany(mappedBy = "titleList", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<TechnicalTask> technicalTasks;

    @OneToMany(mappedBy = "titleList")
    private List<Memo> memos;
}