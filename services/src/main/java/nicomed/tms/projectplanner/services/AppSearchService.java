package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.AppSearchWrapperDto;

public interface AppSearchService {

    AppSearchWrapperDto getSearchResult(String term, Boolean document, Boolean engineer);
}
