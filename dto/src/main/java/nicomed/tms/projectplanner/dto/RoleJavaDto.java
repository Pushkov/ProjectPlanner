package nicomed.tms.projectplanner.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleJavaDto {

    private String name;
    private List<PermissionJavaDto> permissions;
}
