package nicomed.tms.projectplanner.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.role.RoleDto;
import nicomed.tms.projectplanner.dto.role.RoleSimpleDto;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/roles")
public class RoleRestController {

    private final RoleService roleService;

    @GetMapping()
    public Collection<RoleSimpleDto> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleDto findById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateRole(@PathVariable("id") Long id, @Valid @RequestBody RoleDto roleDto) {
        roleService.saveFromDto(id, roleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

    @PostMapping()
    public void createRole(@Valid @RequestBody RoleDto roleDto) {
        roleService.save(roleDto);
    }

    @PutMapping("/{id}/permission/{permissionId}/add")
    public void addPermission(@PathVariable("id") Long id, @PathVariable("permissionId") Long permissionId) {
        roleService.addPermissionById(id, permissionId);
    }

    @PutMapping("/{id}/permission/{permissionId}/remove")
    public void removePermission(@PathVariable("id") Long id, @PathVariable("permissionId") Long permissionId) {
        roleService.removePermissionById(id, permissionId);
    }

}
