package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.services.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tms/planner/api/v1/")
public class PermissionRestController {

    private final PermissionService permissionService;

    @GetMapping("permissions")
    public List<PermissionDto> findAllDto() {
        return permissionService.findAllDto();
    }

    @GetMapping("permissions-by-name/{name}")
    public List<PermissionDto> findPermissionByNameContains(@PathVariable("name") String name) {
        return permissionService.findAllDtoByNameContains(name);
    }

    @GetMapping("permissions-by-role/{id}")
    public List<PermissionDto> findAllDtoByRoleId(@PathVariable("id") Long id) {
        return permissionService.findAllDtoByRole_Id(id);
    }
}
