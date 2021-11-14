package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.AppSearchWrapperDto;
import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.dto.EngineerDto;
import nicomed.tms.projectplanner.services.DocumentService;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.ProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;

import java.util.ArrayList;
import java.util.List;

@JpaImpl
@RequiredArgsConstructor
public class AppSearchServiceImpl {

    private final DocumentService documentService;
    private final EngineerService engineerService;
    private final ProjectService projectService;

    public AppSearchWrapperDto getSearchResult(String term, Boolean document, Boolean engineer) {
        List<DocumentDto> documentDtoList = new ArrayList<>();// getDocumentDtoList(documentFilter);
        List<EngineerDto> engineerDtoList = new ArrayList<>();// getEngineerDtoList(engineerFilter);
        return AppSearchWrapperDto.builder()
                .documentDtoList(documentDtoList)
                .engineerDtoList(engineerDtoList)
                .build();
    }

}
