package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.plan.PlanPointDto;
import nicomed.tms.projectplanner.services.PlanPointService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public PlanPointDto findById(@PathVariable Long id) {
        return planPointService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PlanPointDto dto) {
        planPointService.save(id, dto);
    }

    @PostMapping
    public void create(@RequestBody PlanPointDto dto) {
        planPointService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        planPointService.delete(id);
    }

}
