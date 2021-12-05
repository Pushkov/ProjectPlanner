package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "DOCUMENT_FORMAT")
public class DocumentFormat extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private SheetFormat format;
    private Integer qty;

    @ManyToOne
    private Document document;
}