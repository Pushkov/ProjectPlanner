package nicomed.tms.projectplanner.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.mapper.RoleMapper;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/")
public class RoleRestController {

    private final RoleService roleService;
    private final RoleMapper mapper;


    @GetMapping("roles/{id}")
    public RoleFullDto findRolesById(@PathVariable("id") Long id) {
        return roleService.findFullDtoById(id);
    }

    @GetMapping("roles")
    public List<RoleFullDto> findAllRoles() {
        return roleService.findAllFullDto();
    }

    @PutMapping("roles-names")
    public List<RoleDto> findByRolesExample(@RequestBody RoleDto roleDto) {
        Role role1 = mapper.mapToEntity(roleDto);
        return roleService.findRole(role1);
    }

}
