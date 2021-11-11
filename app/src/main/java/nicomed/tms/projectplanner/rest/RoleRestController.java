package nicomed.tms.projectplanner.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.services.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/")
public class RoleRestController {

    private final RoleService roleService;


    @GetMapping("roles/{id}")
    public RoleFullDto findRolesById(@PathVariable("id") Long id) {
        return roleService.findFullDtoById(id);
    }

    @GetMapping("roles")
    public List<RoleFullDto> findAllRoles() {
        return roleService.findAllFullDto();
    }

    @GetMapping("roles-names")
    public List<RoleDto> findByRolesExample(@RequestParam(required = false) String roleName) {
        if (StringUtils.isEmpty(roleName)) {
            return roleService.findAllDto();
        }
        Role role = Role.builder()
                .name(roleName)
                .build();
        return roleService.findRole(role);
    }

}
