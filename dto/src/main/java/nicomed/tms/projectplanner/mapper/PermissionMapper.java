package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.dto.PermissionJavaDto;
import nicomed.tms.projectplanner.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PermissionMapper {

    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    Permission mapToEntity(PermissionDto dto);

    PermissionDto mapToJaxbDto(Permission permission);

    PermissionJavaDto mapToJavaDto(Permission permission);

    List<PermissionDto> mappingToListJaxbDto(List<Permission> permissions);

    List<PermissionJavaDto> mappingToListJavaDto(List<Permission> permissions);

}
