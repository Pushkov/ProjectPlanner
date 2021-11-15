package nicomed.tms.system.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "SYSTEM_OPTION")
public class SystemOption {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "VALUE")
    private String value;
}