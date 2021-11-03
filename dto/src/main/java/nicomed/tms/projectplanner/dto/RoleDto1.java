package nicomed.tms.projectplanner.dto;

import nicomed.tms.projectplanner.entity.Permission;

import java.util.List;

public class RoleDto1 {

    private String name;
    private List<Permission> permissionsDtoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissionsDtoList() {
        return permissionsDtoList;
    }

    public void setPermissionsDtoList(List<Permission> permissionsDtoList) {
        this.permissionsDtoList = permissionsDtoList;
    }
}
