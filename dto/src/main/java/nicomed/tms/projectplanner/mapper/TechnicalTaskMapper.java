package nicomed.tms.projectplanner.mapper;


import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskForListDto;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TechnicalTaskMapper {

    @Named("TaskForList")
    TechnicalTaskForListDto mapToSimpleDto(TechnicalTask task);

    TechnicalTaskDto mapToDto(TechnicalTask technicalTask);

    @Mapping(target = "id", ignore = true)
    TechnicalTask mapToEntity(TechnicalTaskDto dto);

    @Mapping(target = "id", ignore = true)
    void mapToEntity(@MappingTarget TechnicalTask task, TechnicalTaskDto dto);

}
