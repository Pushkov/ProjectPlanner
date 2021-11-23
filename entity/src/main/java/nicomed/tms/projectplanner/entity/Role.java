package nicomed.tms.projectplanner.entity;

import lombok.*;
import nicomed.tms.listener.RoleEntityListener;

import javax.persistence.*;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EntityListeners(RoleEntityListener.class)
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ROLES_PERMISSIONS",
            joinColumns = @JoinColumn(name = "ROLES_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSIONS_ID")
    )
    private List<Permission> permissions;

}