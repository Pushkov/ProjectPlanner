package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.ProjectDtoShort;
import nicomed.tms.projectplanner.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/projects")
public class ProjectRestController {

    private final ProjectService projectService;

    @GetMapping("")
    public List<ProjectDtoShort> findAllDtoShort() {
        return projectService.findAllDtoShort();
    }

    @GetMapping("/{id}")
    public ProjectDtoShort findDtoShortById(@PathVariable long id) {
        return projectService.findDtoShortById(id);
    }

    @PutMapping("/{id}")
    public void updateDtoShortById(@PathVariable long id, @RequestBody(required = false) ProjectDtoShort dtoShort) {
        ProjectDtoShort dtoShort1 = projectService.findDtoShortById(id);
        dtoShort1.setName(dtoShort1.getName() + "1");
        projectService.save(id, dtoShort1);
    }

}
