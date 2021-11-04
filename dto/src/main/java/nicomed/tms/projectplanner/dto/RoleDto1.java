package nicomed.tms.projectplanner.dto;

import lombok.Getter;
import lombok.Setter;
import nicomed.tms.projectplanner.entity.Permission;

import java.util.List;

@Getter
@Setter
public class RoleDto1 {

    private String name;
    private List<Permission> permissionsDtoList;
}
