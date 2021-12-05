package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.plan.PlanPointDto;
import nicomed.tms.projectplanner.services.PlanPointService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/planpoints")
public class PlanPointRestController {

    private final PlanPointService planPointService;

    @GetMapping
    public Collection<PlanPointDto> findAll() {
        return planPointService.findAll();
    }


}
