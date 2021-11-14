package nicomed.tms.projectplanner.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AppSearchWrapperDto {

    private List<DocumentDto> documentDtoList;
    private List<EngineerDto> engineerDtoList;
}
