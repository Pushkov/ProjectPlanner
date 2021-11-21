package nicomed.tms.projectplanner.dto;

import lombok.Builder;
import lombok.Data;
import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;

import java.util.List;

@Builder
@Data
public class AppSearchWrapperDto {

    private List<DocumentDto> documentDtoList;
    private List<EngineerDto> engineerDtoList;
}
