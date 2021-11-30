package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.PlanPK;
import nicomed.tms.projectplanner.services.PlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/plans")
public class PlanRestController {

    private final PlanService planService;

    @GetMapping
    public Collection<PlanDto> findAll() {
        return planService.findAll();
    }

    @GetMapping("/{id}")
    public PlanDto findById(@PathVariable PlanPK id) {
        return planService.findById(id);
    }
}
