package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.AppSearchWrapperDto;
import nicomed.tms.projectplanner.services.AppSearchService;
import nicomed.tms.projectplanner.services.aspect.LogExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/")
public class ApplicationRestController {

    private final AppSearchService searchService;

    @LogExecutionTime
    @GetMapping("search")
    public AppSearchWrapperDto searchByTerm(@RequestParam(required = false) String term,
                                            @RequestParam(required = false) Boolean document,
                                            @RequestParam(required = false) Boolean engineer
    ) {
        return searchService.getSearchResult(term, document, engineer);
    }

    @GetMapping("engineer-statuses")
    public List<String> getEngineerStatuses() {
        return searchService.getAllEngineerStatuses();
    }
}
