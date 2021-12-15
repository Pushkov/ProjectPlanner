package nicomed.tms.projectplanner.mapper;


import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskForListDto;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import nicomed.tms.projectplanner.qualifier.TechnicalTaskQualifier;
import nicomed.tms.projectplanner.qualifier.WorkshopQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        uses = {ProjectMapper.class,
                TitleListMapper.class,
                WorkshopMapper.class,
                TechnicalTaskQualifier.class,
                WorkshopQualifier.class
        })
public interface TechnicalTaskMapper {

    @Named("TaskForList")
    TechnicalTaskForListDto mapToSimpleDto(TechnicalTask task);

    @Mapping(target = "projects", source = "task.projects")
    @Mapping(target = "titleListYear", source = "task.titleList.id")
    @Mapping(target = "workshopId", source = "task.workshop.id")
    @Mapping(target = "workshopName", source = "task.workshop.name")
    TechnicalTaskDto mapToDto(TechnicalTask task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "titleList", ignore = true)
    @Mapping(target = "workshop", ignore = true)
    @Mapping(target = "baseTask", ignore = true)
    @Mapping(target = "extensions", ignore = true)
    TechnicalTask mapToEntity(TechnicalTaskDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "titleList", ignore = true)
    @Mapping(target = "workshop", source = "workshopId", qualifiedByName = "workshopId")
//    @Mapping(target = "workshop", ignore = true)
    @Mapping(target = "baseTask", ignore = true)
//    @Mapping(target = "extensions", ignore = true)"taskForList"
    @Mapping(target = "extensions", source = "extensions", qualifiedByName = "taskForList")
    void mapToEntity(@MappingTarget TechnicalTask task, TechnicalTaskDto dto);

}
