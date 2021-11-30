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
                DocumentMapper.class})
public interface ProjectMapper extends DateTimeMapper {


    @Named("ProjectSimpleDto")
    @Mapping(target = "id", source = "project.id")
    @Mapping(target = "designation", source = "project.designation")
    @Mapping(target = "name", source = "project.name")
    ProjectSimpleDto mapToSimpleDto(Project project);

    @Mapping(target = "id", source = "project.id")
    @Mapping(target = "designation", source = "project.designation")
    @Mapping(target = "name", source = "project.name")
    @Mapping(target = "projects", source = "project.projects")
//    @Mapping(target = "documentsDto", source = "project.documents")
    @Mapping(target = "basicProject", source = "project.basicProject")
    @Mapping(target = "departmentName", source = "project.department.name")
    @Mapping(target = "workshopName", source = "project.workshop.name")
    @Mapping(target = "task", source = "project.task")
    @Mapping(target = "memo", source = "project.memo")
    @Mapping(target = "projectApprovalsDto", source = "project.projectApprovals")
    ProjectDto mapToDto(Project project);


    Project mapToEntity(ProjectDto dto);


    void mapToEntity(@MappingTarget Project project, ProjectDto dtoShort);

    ProjectForListDto map(Project project);

}
