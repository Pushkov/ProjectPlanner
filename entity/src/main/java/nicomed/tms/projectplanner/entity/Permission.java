package nicomed.tms.projectplanner.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import nicomed.tms.projectplanner.enums.UserPermission;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @Enumerated(EnumType.STRING)
    private UserPermission name;

    @ManyToMany(mappedBy = "permissions")
    @JsonBackReference
    private List<Role> roles;

}