package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectForListDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(uses =
        {DocumentMapper.class,
                DepartmentMapper.class,
                WorkshopMapper.class,
                EngineerMapper.class,
                ProjectApprovalsMapper.class,
                DocumentMapper.class,
                TechnicalTaskMapper.class,
                MemoMapper.class})
public interface ProjectMapper extends DateTimeMapper {

    @Named("ProjectSimpleDto")
    ProjectSimpleDto mapToSimpleDto(Project project);

    @Mapping(target = "projects", source = "project.projects", qualifiedByName = "ProjectSimpleDto")
    @Mapping(target = "basicProject", source = "project.basicProject", qualifiedByName = "ProjectSimpleDto")
    @Mapping(target = "departmentId", source = "project.department.id")
    @Mapping(target = "departmentName", source = "project.department.name")
    @Mapping(target = "workshopId", source = "project.workshop.id")
    @Mapping(target = "workshopName", source = "project.workshop.name")
    @Mapping(target = "task", source = "project.task", qualifiedByName = "TaskForList")
    @Mapping(target = "memo", source = "project.memo", qualifiedByName = "MemoForList")
    @Mapping(target = "projectApprovalsDto", source = "project.projectApprovals")
    ProjectDto mapToDto(Project project);

    @Mapping(target = "id", ignore = true)
    Project mapToEntity(ProjectDto dto);

    @Mapping(target = "id", ignore = true)
    void mapToEntity(@MappingTarget Project project, ProjectDto dtoShort);

    ProjectForListDto map(Project project);

}
