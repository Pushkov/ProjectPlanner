package nicomed.tms.projectplanner.entity;

import lombok.*;
import nicomed.tms.projectplanner.enums.Format;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class DocumentFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Format format;
    private int qty;

    @ManyToOne
    private Document document;
}