package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.project.ProjectApprovalsDto;
import nicomed.tms.projectplanner.entity.ProjectApprovals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {EngineerMapper.class})
public interface ProjectApprovalsMapper {

    @Mapping(target = "designerName", source = "designer.lastName")
    @Mapping(target = "verifierName", source = "verifier.lastName")
    @Mapping(target = "normControlName", source = "normControl.lastName")
    @Mapping(target = "agreeName", source = "agree.lastName")
    @Mapping(target = "approveName", source = "approve.lastName")
    ProjectApprovalsDto mapToDto(ProjectApprovals entity);

    ProjectApprovals mapToEntity(ProjectApprovalsDto dto);


}
