package nicomed.tms.system.entity;

import lombok.*;
import nicomed.tms.listener.SystemOptionalEntityListener;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EntityListeners(SystemOptionalEntityListener.class)
@Entity
@Table(name = "SYSTEM_OPTION")
public class SystemOptionEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "VALUE")
    private String value;
}