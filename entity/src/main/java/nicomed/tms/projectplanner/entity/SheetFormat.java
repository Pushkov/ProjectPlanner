package nicomed.tms.projectplanner.entity;

import lombok.*;
import nicomed.tms.projectplanner.converter.FormatConverter;
import nicomed.tms.projectplanner.enums.Format;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "FORMAT")
public class SheetFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Convert(converter = FormatConverter.class)
    @Column(name = "FORMAT")
    private Format format;
    @Column(name = "SIZE")
    private Float size;

}