package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.project.ProjectApprovalsDto;
import nicomed.tms.projectplanner.entity.ProjectApprovals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {EngineerMapper.class})
public interface ProjectApprovalsMapper {

    @Mapping(target = "designerId", source = "designer.id")
    @Mapping(target = "designerName", source = "designer.lastName")
    @Mapping(target = "verifierId", source = "verifier.id")
    @Mapping(target = "verifierName", source = "verifier.lastName")
    @Mapping(target = "normControlId", source = "normControl.id")
    @Mapping(target = "normControlName", source = "normControl.lastName")
    @Mapping(target = "agreeId", source = "agree.id")
    @Mapping(target = "agreeName", source = "agree.lastName")
    @Mapping(target = "approveId", source = "approve.id")
    @Mapping(target = "approveName", source = "approve.lastName")
    ProjectApprovalsDto mapToDto(ProjectApprovals entity);

    @Mapping(target = "designer", ignore = true)
    @Mapping(target = "verifier", ignore = true)
    @Mapping(target = "normControl", ignore = true)
    @Mapping(target = "agree", ignore = true)
    @Mapping(target = "approve", ignore = true)
    ProjectApprovals mapToEntity(ProjectApprovalsDto dto);


}
