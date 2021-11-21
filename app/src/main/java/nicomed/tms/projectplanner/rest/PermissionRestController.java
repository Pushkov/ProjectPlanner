package nicomed.tms.projectplanner.rest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.services.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/permissions")
public class PermissionRestController {

    private final PermissionService permissionService;

    @ApiOperation(value = "Get all permissions", response = Permission.class, responseContainer = "List")
    @GetMapping("")
    public List<PermissionDto> findAllDto() {
        return (List<PermissionDto>) permissionService.findAll();
    }

    @ApiOperation(value = "Find permission by ID", response = Permission.class, responseContainer = "Object")
    @GetMapping("/{id}")
    public PermissionDto findDtoById(@PathVariable("id") Long id) {
        return permissionService.findById(id);
    }

    @PutMapping("/{id}")
    public void updatePermission(@PathVariable("id") Long id, @RequestBody PermissionDto dto) {
        permissionService.save(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable("id") Long id) {
        permissionService.delete(id);
    }

    @PostMapping("")
    public void createPermission(@RequestBody PermissionDto dto) {
        permissionService.save(dto);
    }

    @ApiOperation(value = "Get all permissions containing name", response = Permission.class, responseContainer = "List")
    @GetMapping("/name/{name}")
    public List<PermissionDto> findPermissionByNameContains(@PathVariable("name") String name) {
        return permissionService.findAllDtoByNameContains(name);
    }

    @ApiOperation(value = "Get all permissions by Role.id", response = Permission.class, responseContainer = "List")
    @GetMapping("/role/{id}")
    public List<PermissionDto> findAllDtoByRoleId(@PathVariable("id") Long id) {
        return permissionService.findAllDtoByRole_Id(id);
    }
}
