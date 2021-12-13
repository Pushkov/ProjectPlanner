package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.AppSearchWrapperDto;

import java.util.List;

public interface AppSearchService {

    AppSearchWrapperDto getSearchResult(String term, Boolean document, Boolean project, Boolean engineer);

    List<String> getAllEngineerStatuses();
}
