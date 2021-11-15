package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.Role;

import java.util.List;

public interface RoleService extends CrudService<Role, Long> {

    RoleFullDto findFullDtoById(Long id);

    RoleFullDto findFullDtoByName(String name);

    RoleDto findDtoById(Long id);

    List<RoleFullDto> findAllFullDto();

    List<RoleDto> findAllDto();

    List<RoleDto> findRole(Role role);
}
