package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.repository.specification.filter.PlanFilter;
import nicomed.tms.projectplanner.services.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/plans")
public class PlanRestController {

    private final PlanService planService;

    @GetMapping("/{year}/{month}/{id}")
    public PlanDto findById(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Long id) {
        return planService.findByIdFields(year, month, id);
    }

    @GetMapping
    public Collection<PlanDto> search(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Long depId) {
        if (year == null
                && month == null
                && depId == null) {
            return planService.findAll();
        }
        PlanFilter filter = PlanFilter.builder()
                .year(year)
                .month(month)
                .depId(depId)
                .build();
        return planService.search(filter);
    }
}
