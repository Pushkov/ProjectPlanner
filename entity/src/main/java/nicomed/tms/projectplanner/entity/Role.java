package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "ROLES_PERMISSIONS",
            joinColumns = @JoinColumn(name = "ROLES_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSIONS_ID")
    )
    private List<Permission> permissions;

}