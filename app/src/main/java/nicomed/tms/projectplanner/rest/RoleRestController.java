package nicomed.tms.projectplanner.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("roles/{name}")
//    public RoleFullDto findRolebyName(@PathVariable("name") String name) {
//        return roleService.findFullDtoByName(name);
//    }

    @GetMapping("roles")
    public List<RoleFullDto> findAllRoles() {
        return roleService.findAllFullDto();
    }

    @GetMapping("roles-names")
    public List<RoleDto> findAllRolesNames() {
        return roleService.findAllDto();
    }

}
