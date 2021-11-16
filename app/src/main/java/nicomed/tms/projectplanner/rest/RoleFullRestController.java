package nicomed.tms.projectplanner.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.mapper.RoleMapper;
import nicomed.tms.projectplanner.services.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/roles/full")
public class RoleFullRestController {

    private final RoleService roleService;
    private final RoleMapper mapper;

    @GetMapping("")
    public List<RoleFullDto> findAllRoles() {
        return roleService.findAllFullDto();
    }

    @GetMapping("/{id}")
    public RoleFullDto findRolesById(@PathVariable("id") Long id) {
        return roleService.findFullDtoById(id);
    }


    @PutMapping("/{id}")
    public void updateRole(@PathVariable("id") Long id, @RequestBody RoleFullDto roleFullDto) {
//        Role role = mapper.mapFullDtoToEntity(roleFullDto);
//        role.setId(id);
        roleService.saveFromDto(id, roleFullDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

    @PostMapping("")
    public void createRole(@RequestBody RoleDto roleDto) {
        roleService.save(mapper.mapToEntity(roleDto));
    }


}
