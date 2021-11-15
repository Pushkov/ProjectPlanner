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
@Table(name = "DOCUMENT")
public class Document extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "DOCUMENT_PROJECTS",
            joinColumns = @JoinColumn(name = "DOCUMENTS_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJECTS_ID"))
    private List<Project> projects;

    @OneToMany(mappedBy = "document")
    private List<DocumentFormat> documentFormats;

    @Embedded
    private DocumentApprovals documentApprovals;
}