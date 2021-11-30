package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.project.ProjectDto;
import nicomed.tms.projectplanner.dto.project.ProjectSimpleDto;
import nicomed.tms.projectplanner.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/projects")
public class ProjectRestController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectSimpleDto> findAll() {
        return (List<ProjectSimpleDto>) projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectDto findById(@PathVariable long id) {
        return projectService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateProject(@PathVariable long id, @RequestBody ProjectDto dto) {
        projectService.save(id, dto);
    }

    @PostMapping
    public void createProject(@RequestBody ProjectDto dto) {
        projectService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.delete(id);
    }
}
