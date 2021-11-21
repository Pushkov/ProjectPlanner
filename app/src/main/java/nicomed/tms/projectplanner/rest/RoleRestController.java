package nicomed.tms.projectplanner.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.mapper.RoleMapper;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/roles")
public class RoleRestController {

    private final RoleService roleService;
    private final RoleMapper mapper;

    @GetMapping("")
    public List<RoleDto> findAllRoles() {
        return (List<RoleDto>) roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleDto findRolesById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @PutMapping("/example")
    public List<RoleDto> findByRolesExample(@RequestBody RoleDto roleDto) {
        Role role = mapper.mapToEntity(roleDto);
        return roleService.findDtoByExample(role);
    }
}
