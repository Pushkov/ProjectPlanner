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
        return permissionService.findAllDto();
    }

    @GetMapping("/{id}")
    public PermissionDto findDtoById(@PathVariable("id") String id) {
        return permissionService.findDtoById(id);
    }

    @PutMapping("/{id}")
    public void updatePermission(@PathVariable("id") String id, @RequestBody PermissionDto dto) {
        permissionService.save(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable("id") String id) {
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
