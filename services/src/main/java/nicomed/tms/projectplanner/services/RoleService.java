package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.RoleDto;
import nicomed.tms.projectplanner.dto.RoleFullDto;
import nicomed.tms.projectplanner.entity.Role;

import java.util.List;

public interface RoleService extends CrudService<Role, Long> {

    RoleDto findDtoById(Long id);

    List<RoleDto> findAllDto();

    List<RoleDto> findDtoByExample(Role role);


    RoleFullDto findFullDtoById(Long id);

    List<RoleFullDto> findAllFullDto();

    List<RoleFullDto> findFullDtoByExample(Role role);

    void createRole(RoleFullDto dto);

    void saveFromDto(Long id, RoleFullDto dto);

}
