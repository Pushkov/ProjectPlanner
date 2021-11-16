package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.SystemLogDto;
import nicomed.tms.system.entity.SystemLogEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SystemLogMapper {

    SystemLogDto mapToDto(SystemLogEntity entity);

    List<SystemLogDto> mapListToDto(List<SystemLogEntity> entity);
}
