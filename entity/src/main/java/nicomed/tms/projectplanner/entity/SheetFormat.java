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
@Table(name = "format")
public class SheetFormat {
    @Id
    @Enumerated(EnumType.STRING)
    private Format format;
    private String name;
    private Float size;

}