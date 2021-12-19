package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.DocumentApprovalsDto;
import nicomed.tms.projectplanner.entity.DocumentApprovals;
import nicomed.tms.projectplanner.qualifier.EngineerQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {EngineerMapper.class, EngineerQualifier.class})
public interface DocumentApprovalsMapper {

    @Mapping(target = "designerId", source = "designer.id")
    @Mapping(target = "designerName", source = "designer.lastName")
    @Mapping(target = "verifierId", source = "verifier.id")
    @Mapping(target = "verifierName", source = "verifier.lastName")
    @Mapping(target = "normControlId", source = "normControl.id")
    @Mapping(target = "normControlName", source = "normControl.lastName")
    DocumentApprovalsDto mapToDto(DocumentApprovals entity);


    @Mapping(target = "designer", source = "designerId", qualifiedByName = "engineerById")
    @Mapping(target = "verifier", source = "verifierId", qualifiedByName = "engineerById")
    @Mapping(target = "normControl", source = "normControlId", qualifiedByName = "engineerById")
    DocumentApprovals mapToEntity(DocumentApprovalsDto dto);

}
