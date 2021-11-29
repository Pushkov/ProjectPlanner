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

    @GetMapping("")
    public List<ProjectSimpleDto> findAllFull() {
        return (List<ProjectSimpleDto>) projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectDto findDtoShortById(@PathVariable long id) {
        return projectService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateDtoShortById(@PathVariable long id, @RequestBody(required = false) ProjectSimpleDto dtoShort) {
        ProjectSimpleDto dtoShort1 = projectService.findById(id);
        dtoShort1.setName(dtoShort1.getName() + "1");
        projectService.save(dtoShort1);
    }

}
