package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.DocumentApprovalsDto;
import nicomed.tms.projectplanner.entity.DocumentApprovals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {EngineerMapper.class})
public interface DocumentApprovalsMapper {

    @Mapping(target = "designerName", source = "designer.lastName")
    @Mapping(target = "verifierName", source = "verifier.lastName")
    @Mapping(target = "normControlName", source = "normControl.lastName")
    DocumentApprovalsDto mapToDto(DocumentApprovals entity);

    DocumentApprovals mapToEntity(DocumentApprovalsDto dto);


//    default String mapDateToString(OffsetDateTime offsetDateTime) {
//        return offsetDateTime.toString();
//    }
}
