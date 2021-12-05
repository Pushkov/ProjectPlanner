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
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Document extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_APPROVED")
    private Boolean isApproved;

    @OneToMany(mappedBy = "document")
    private List<DocumentFormat> documentFormats;
}