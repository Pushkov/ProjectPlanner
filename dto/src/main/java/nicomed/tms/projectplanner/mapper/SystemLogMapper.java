package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.system.SystemLogDto;
import nicomed.tms.system.entity.SystemLogEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SystemLogMapper {

    SystemLogDto mapToDto(SystemLogEntity entity);
}
