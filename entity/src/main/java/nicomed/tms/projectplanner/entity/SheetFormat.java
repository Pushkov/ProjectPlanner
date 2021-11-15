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
@Table(name = "FORMAT")
public class SheetFormat {
    @Id
    @Enumerated(EnumType.STRING)
    private Format format;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SIZE")
    private Float size;

}