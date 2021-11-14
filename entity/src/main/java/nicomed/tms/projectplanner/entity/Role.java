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
public class Role extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

@ManyToMany
@JoinTable(name = "roles_permissions",
        joinColumns = @JoinColumn(name = "roles_id"),
        inverseJoinColumns = @JoinColumn(name = "permissions_id")
)
private List<Permission> permissions;

}