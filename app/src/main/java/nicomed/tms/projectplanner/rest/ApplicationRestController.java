package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.AppSearchWrapperDto;
import nicomed.tms.projectplanner.services.AppSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/")
public class ApplicationRestController {

    private final AppSearchService searchService;

    @GetMapping("search")
    public AppSearchWrapperDto searchByTerm(@RequestParam(required = false) String term,
                                            @RequestParam(required = false) Boolean document,
                                            @RequestParam(required = false) Boolean engineer
    ) {
        return searchService.getSearchResult(term, document, engineer);
    }

}
