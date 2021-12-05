package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;
import nicomed.tms.projectplanner.repository.specification.filter.PlanProjectFilter;
import nicomed.tms.projectplanner.services.PlanProjectService;
import org.apache.commons.lang3.StringUtils;
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

    @GetMapping("/search")
    public List<PlanProjectDto> search(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) String depName,
            @RequestParam(required = false) String termProjectDes,
            @RequestParam(required = false) String termProjectName,
            @RequestParam(required = false) String termTaskName) {
        if (year == null
                && month == null
                && StringUtils.isEmpty(depName)
                && StringUtils.isEmpty(termProjectDes)
                && StringUtils.isEmpty(termProjectName)
                && StringUtils.isEmpty(termTaskName)) {
            return planProjectService.findAll();
        }
        PlanProjectFilter filter = PlanProjectFilter.builder()
                .year(year)
                .month(month)
                .depName(depName)
                .termProjectDes(termProjectDes)
                .termProjectName(termProjectName)
                .termTaskName(termTaskName)
                .build();
        return planProjectService.search(filter);
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
