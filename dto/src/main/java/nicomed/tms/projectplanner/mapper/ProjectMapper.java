package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectForListDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.enums.ProjectStatus;
import nicomed.tms.projectplanner.qualifier.*;
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
                MemoMapper.class,
                PlanPointMapper.class,
                DocumentMapper.class,
                ProjectQualifier.class,
                PlanPointQualifier.class,
                DocumentQualifier.class,
                DepartmentQualifier.class,
                WorkshopQualifier.class,
                TechnicalTaskQualifier.class,
                MemoQualifier.class
        })
public interface ProjectMapper extends DateTimeMapper {

    @Named("ProjectSimpleDto")
    @Mapping(target = "departmentId", source = "project.department.id")
    @Mapping(target = "departmentName", source = "project.department.name")
    @Mapping(target = "workshopId", source = "project.workshop.id")
    @Mapping(target = "workshopName", source = "project.workshop.name")
    @Mapping(target = "task", source = "project.task", qualifiedByName = "TaskForList")
    @Mapping(target = "memo", source = "project.memo", qualifiedByName = "MemoForList")
    @Mapping(target = "status", source = "project.status.name")
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
    @Mapping(target = "documentsDto", source = "project.documents", qualifiedByName = "documentSimpleDto")
    @Mapping(target = "status", source = "status.name")
    ProjectDto mapToDto(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "basicProject", source = "basicProject", qualifiedByName = "projectBySimple")
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "planPoints", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "department", source = "departmentId", qualifiedByName = "departmentById")
    @Mapping(target = "workshop", source = "workshopId", qualifiedByName = "workshopId")
    @Mapping(target = "task", source = "task", qualifiedByName = "taskByForList")
    @Mapping(target = "memo", source = "memo", qualifiedByName = "memoByForList")
    @Mapping(target = "projectApprovals", source = "dto.projectApprovalsDto")
    @Mapping(target = "status", expression = "java(getStatusByName(dto.getStatus()))")
    Project mapToEntity(ProjectDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "basicProject", source = "basicProject", qualifiedByName = "projectBySimple")
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "planPoints", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "department", source = "departmentId", qualifiedByName = "departmentById")
    @Mapping(target = "workshop", source = "workshopId", qualifiedByName = "workshopId")
    @Mapping(target = "task", source = "task", qualifiedByName = "taskByForList")
    @Mapping(target = "memo", source = "memo", qualifiedByName = "memoByForList")
    @Mapping(target = "projectApprovals", source = "dto.projectApprovalsDto")
    @Mapping(target = "status", expression = "java(getStatusByName(dto.getStatus()))")
    void mapToEntity(@MappingTarget Project project, ProjectDto dto);

    ProjectForListDto map(Project project);

    default ProjectStatus getStatusByName(String statusName) {
        return ProjectStatus.getByName(statusName);
    }

}
