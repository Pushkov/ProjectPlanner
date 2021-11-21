package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.system.SystemOptionDto;
import nicomed.tms.system.entity.SystemOptionEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SystemOptionMapper {

    SystemOptionDto mapToDto(SystemOptionEntity entity);

    SystemOptionEntity mapToEntity(SystemOptionDto dto);

}
