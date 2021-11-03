package nicomed.tms.projectplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.services.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PermissionRestController {

    private final PermissionService permissionService;

    @GetMapping("api/perm")
    public List<Permission> findAll() {
        return (List<Permission>) permissionService.findAll();
    }

    @GetMapping("api/perm-list/{name}")
    public List<Permission> findAllContainsName(@PathVariable("name") String name) {
        return permissionService.findAllByNameContains(name);
    }

    @GetMapping("api/perm-dto")
    public List<Permission> findAllDto() {
        return (List<Permission>) permissionService.findAll();
    }

    @GetMapping("api/perm-role/{id}")
    public List<Permission> findAllRole(@PathVariable("id") Long id) {
        return permissionService.findByRoles_Id(id);
    }


}
