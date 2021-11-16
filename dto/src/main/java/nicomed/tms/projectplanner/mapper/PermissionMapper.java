package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.entity.Permission;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PermissionMapper {

    Permission mapToEntity(PermissionDto dto);

    PermissionDto mapToDto(Permission permission);

    List<PermissionDto> mapToListDto(List<Permission> permissions);

    List<Permission> mapToListEntity(List<PermissionDto> permissions);
}
