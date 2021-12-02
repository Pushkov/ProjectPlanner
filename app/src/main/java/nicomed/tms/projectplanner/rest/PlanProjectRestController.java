package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;
import nicomed.tms.projectplanner.services.PlanProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/planprojects")
public class PlanProjectRestController {

    private final PlanProjectService planProjectService;

    @GetMapping
    public Collection<PlanProjectDto> findAll() {
        return planProjectService.findAll();
    }

    @GetMapping("/{id}")
    public PlanProjectDto findById(@PathVariable long id) {
        return planProjectService.findById(id);
    }

    @PutMapping("/search")
    public Collection<PlanProjectDto> findByExample(@RequestBody PlanProjectDto exampleDto) {
        return planProjectService.search(exampleDto);
    }

    @GetMapping("/years")
    public List<Integer> findAllYears() {
        return planProjectService.findAllYears();
    }

    @GetMapping("/months")
    public List<Integer> findAllMonths() {
        return planProjectService.findAllMonths();
    }

    @GetMapping("/departments")
    public List<String> findAllDepNames() {
        return planProjectService.findAllDepartmentNames();
    }

}
