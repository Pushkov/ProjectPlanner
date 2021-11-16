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
@Table(name = "PERMISSION")
public class Permission {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
@Id
@Column(name = "NAME")
private String name;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}