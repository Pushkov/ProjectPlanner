package nicomed.tms.projectplanner.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleJavaDto;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class RoleRestController {

    private final RoleService roleService;

    @GetMapping("roles")
    public List<Role> findAllRoles() {
        return (List<Role>) roleService.findAll();
    }

    @GetMapping("roles/{id}")
    public Role findAllRoles(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }


    @GetMapping("roles-dto/java")
    public List<RoleJavaDto> findAllDtoRoles() {
        return roleService.findAllJavaDto();
    }

    @GetMapping("roles-dto/jaxb")
    public List<RoleDto> findAllJaxbDtoRoles() {
        return roleService.findAllJaxbDto();
    }
}
