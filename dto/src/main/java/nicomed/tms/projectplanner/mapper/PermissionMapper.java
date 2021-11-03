package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    Permission mapToEntity(PermissionDto dto);

    PermissionDto mapToDto(Permission permission);
}
