package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.AppSearchWrapperDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.enums.Status;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import nicomed.tms.projectplanner.repository.specification.filter.ProjectFilter;
import nicomed.tms.projectplanner.services.AppSearchService;
import nicomed.tms.projectplanner.services.DocumentService;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.ProjectService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@JpaImpl
@RequiredArgsConstructor
public class AppSearchServiceImpl implements AppSearchService {

    private final DocumentService documentService;
    private final EngineerService engineerService;
    private final ProjectService projectService;

    @Override
    public AppSearchWrapperDto getSearchResult(String term, Boolean document, Boolean project, Boolean engineer) {
        return AppSearchWrapperDto.builder()
                .documentSimpleDtoList(findDocumentsByTerm(document, term))
                .projectDtoList(findProjectsByTerm(project, term))
                .engineerDtoList(findEngineersByTerm(engineer, term))
                .build();
    }

    private List<DocumentSimpleDto> findDocumentsByTerm(Boolean hasSearch, String term) {
        if (hasSearch && !StringUtils.isEmpty(term)) {
            DocumentFilter filter = DocumentFilter.builder()
                    .term(term)
                    .build();
            return documentService.search(filter);
        } else {
            return Collections.emptyList();
        }
    }

    private List<ProjectSimpleDto> findProjectsByTerm(Boolean hasSearch, String term) {
        if (hasSearch && !StringUtils.isEmpty(term)) {
            ProjectFilter filter = ProjectFilter.builder()
                    .term(term)
                    .build();
            return projectService.search(filter);
        } else {
            return Collections.emptyList();
        }
    }

    private List<EngineerDto> findEngineersByTerm(Boolean hasSearch, String term) {
        if (hasSearch && !StringUtils.isEmpty(term)) {
            EngineerFilter filter = EngineerFilter.builder()
                    .term(term)
                    .build();
            return engineerService.search(filter);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> getAllEngineerStatuses() {
        return Arrays.stream(Status.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
