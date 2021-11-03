package nicomed.tms.projectplanner.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping("rolesdto")
//    public List<RoleDto1> findAllDtoRoles() {
//        return roleService.findAllDto1();
//    }

}
