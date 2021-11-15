package nicomed.tms.projectplanner.mapper;


import nicomed.tms.projectplanner.dto.TechnicalTaskDtoShort;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TechnicalTaskMapper {

    TechnicalTaskDtoShort mapToDtoShort(TechnicalTask technicalTask);

    List<TechnicalTaskDtoShort> mapToListDtoShort(List<TechnicalTask> technicalTaskList);
}
