package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import nicomed.tms.projectplanner.services.EngineerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/")
public class EngineerRestController {

    private final EngineerService engineerService;

    @GetMapping("engineers")
    public List<EngineerDto> findAllListDto() {
        return (List<EngineerDto>) engineerService.findAll();
    }

    @GetMapping("engineers/find/{id}")
    public EngineerDto findById(@PathVariable Long id) {
        return engineerService.findById(id);
    }

    @GetMapping("engineers/search")
    public List<EngineerDto> search(@RequestParam(required = false) String term) {
        if (StringUtils.isEmpty(term)) {
            return null;
        }
        EngineerFilter filter = EngineerFilter.builder()
                .term(term)
                .build();
        return engineerService.search(filter);
    }
}
