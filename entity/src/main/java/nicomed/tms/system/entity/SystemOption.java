package nicomed.tms.system.entity;

import lombok.*;

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
    private String id;

    private String value;
}