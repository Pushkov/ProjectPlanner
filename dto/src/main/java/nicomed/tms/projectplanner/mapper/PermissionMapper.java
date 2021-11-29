package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PermissionMapper {

    PermissionDto mapToDto(Permission permission);

    Permission mapToEntity(PermissionDto dto);
}
