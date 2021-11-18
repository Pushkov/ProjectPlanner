package nicomed.tms.projectplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "ID")
@Entity
@Table(name = "DOCUMENT")
public class Document extends DocumentBasic {

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