package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.PermissionDto;
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

    @GetMapping("api/perm/{id}")
    public Permission findPermissionById(@PathVariable("id") Long id) {
        return permissionService.findById(id);
    }

    @GetMapping("api/perm-dto/jaxb/{id}")
    public PermissionDto findPermissionJaxbDtoById(@PathVariable("id") Long id) {
        return permissionService.findPermissionJaxbDtoById(id);
    }

    @GetMapping("api/perm-list/{name}")
    public List<Permission> findAllContainsName(@PathVariable("name") String name) {
        return permissionService.findAllByNameContains(name);
    }

    @GetMapping("api/perm-dto/jaxb")
    public List<PermissionDto> findAllJaxbDto() {
        return permissionService.findAllJaxbDto();
    }

    @GetMapping("api/perm-dto/java")
    public List<PermissionDto> findAllJavaDto() {
        return permissionService.findAllJaxbDto();
    }

    @GetMapping("api/perm-role/{id}")
    public List<Permission> findAllRole(@PathVariable("id") Long id) {
        return permissionService.findByRoles_Id(id);
    }
}
