package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.entity.Project;
import org.mapstruct.MappingTarget;

//@Mapper
public interface ProjectExtMapper<D extends ProjectSimpleDto> {

    D mapToDto(@MappingTarget D dto, Project entity);

    Project mapToEntity(D dto);


}
