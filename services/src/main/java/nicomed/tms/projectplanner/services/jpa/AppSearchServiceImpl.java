package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.AppSearchWrapperDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import nicomed.tms.projectplanner.services.AppSearchService;
import nicomed.tms.projectplanner.services.DocumentService;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.ProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@JpaImpl
@RequiredArgsConstructor
public class AppSearchServiceImpl implements AppSearchService {

    private final DocumentService documentService;
    private final EngineerService engineerService;
    private final ProjectService projectService;

    @Override
    public AppSearchWrapperDto getSearchResult(String term, Boolean document, Boolean engineer) {

        List<DocumentSimpleDto> documentSimpleDtoList = new ArrayList<>();
        if (document && !StringUtils.isEmpty(term)) {
            DocumentFilter filter = DocumentFilter.builder()
                    .term(term)
                    .build();
            documentSimpleDtoList.addAll(documentService.search(filter));
        }
        List<EngineerDto> engineerDtoList = new ArrayList<>();
        if (engineer && !StringUtils.isEmpty(term)) {
            EngineerFilter filter = EngineerFilter.builder()
                    .term(term)
                    .build();
            engineerDtoList.addAll(engineerService.search(filter));
        }
        return AppSearchWrapperDto.builder()
                .documentSimpleDtoList(documentSimpleDtoList)
                .engineerDtoList(engineerDtoList)
                .build();
    }

}
