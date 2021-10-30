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
public class Document extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private String name;

    @ManyToMany
    private List<Project> projects;

    @OneToMany(mappedBy = "document")
    private List<DocumentFormat> documentFormats;

    @Embedded
    private DocumentApprovals documentApprovals;
}