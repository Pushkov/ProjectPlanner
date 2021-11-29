package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectDtoFull;
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
    public List<ProjectDto> findAllFull() {
        return (List<ProjectDto>) projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectDtoFull findDtoShortById(@PathVariable long id) {
        return projectService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateDtoShortById(@PathVariable long id, @RequestBody(required = false) ProjectDto dtoShort) {
        ProjectDto dtoShort1 = projectService.findById(id);
        dtoShort1.setName(dtoShort1.getName() + "1");
        projectService.save(dtoShort1);
    }

}
