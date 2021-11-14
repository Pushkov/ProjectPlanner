package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.services.jpa.AppSearchServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/")
public class ApplicationRestController {

    private final AppSearchServiceImpl searchService;

    @GetMapping("search")
    public DocumentDto searchByTerm(@RequestParam(required = false) String term,
                                    @RequestParam(required = false) Boolean document,
                                    @RequestParam(required = false) Boolean engineer
    ) {
        //todo
        return null;
    }

}
