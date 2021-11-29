package nicomed.tms.projectplanner.mapper;


import nicomed.tms.projectplanner.dto.TechnicalTaskDto;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TechnicalTaskMapper {

    TechnicalTaskDto mapToDto(TechnicalTask technicalTask);

    TechnicalTask mapToEntity(TechnicalTaskDto dto);

}
