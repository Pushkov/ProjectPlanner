package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectDtoFull;
import nicomed.tms.projectplanner.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses =
        {DocumentMapper.class,
                DepartmentMapper.class,
                WorkshopMapper.class,
                EngineerMapper.class,
                ProjectApprovalsMapper.class,
                DocumentMapper.class})
public interface ProjectMapper {


    @Mapping(target = "id", source = "project.id")
    @Mapping(target = "designation", source = "project.designation")
    @Mapping(target = "name", source = "project.name")
    ProjectDto mapToDto(ProjectDto dto, Project project);

    Project mapToEntity(ProjectDto dtoShort);

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
    ProjectDtoFull mapToDto(@MappingTarget ProjectDtoFull dto, Project project);

    Project mapToEntity(ProjectDtoFull dtoShort);
}
