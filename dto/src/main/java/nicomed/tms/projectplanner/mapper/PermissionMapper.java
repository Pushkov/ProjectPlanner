package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    Permission mapToEntity(PermissionDto dto);

    PermissionDto mapToDto(Permission permission);

    List<PermissionDto> mapToListDto(List<Permission> permissions);
}
