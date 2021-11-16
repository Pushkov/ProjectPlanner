package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.SystemOptionDto;
import nicomed.tms.system.entity.SystemOptionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SystemOptionMapper {

    SystemOptionDto mapToDto(SystemOptionEntity entity);

    List<SystemOptionDto> mapListToDto(List<SystemOptionEntity> entity);
}
