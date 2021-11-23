package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.role.RoleDto;
import nicomed.tms.projectplanner.dto.role.RoleSimpleDto;

public interface RoleService extends CrudDoubleDtoService<RoleDto, RoleSimpleDto, Long> {

    void saveFromDto(Long id, RoleDto dto);

}
