package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.entity.PlanProject;
import nicomed.tms.projectplanner.repository.PlanProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/planprojects")
public class PlanProjectRestController {

    private final PlanProjectRepository planProjectRepository;

    @GetMapping
    public List<PlanProject> findAll() {
        return planProjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public PlanProject findById(@PathVariable long id) {
        return planProjectRepository.findById(id)
                .orElseThrow();
    }

    @GetMapping("/years")
    public List<Integer> findAllYears() {
        return planProjectRepository.findAllYears();
    }

    @GetMapping("/months")
    public List<Integer> findAllMonths() {
        return planProjectRepository.findAllMonths();
    }

    @GetMapping("/departments")
    public List<String> findAllDepNames() {
        return planProjectRepository.findAllDepartmentNames();
    }

}
