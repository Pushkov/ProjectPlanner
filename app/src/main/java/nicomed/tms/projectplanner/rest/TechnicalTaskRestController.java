package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;
import nicomed.tms.projectplanner.services.TechnicalTaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/tasks")
public class TechnicalTaskRestController {

    private final TechnicalTaskService technicalTaskService;

    @GetMapping
    public List<TechnicalTaskDto> findAll() {
        return (List<TechnicalTaskDto>) technicalTaskService.findAll();
    }

    @GetMapping("/{id}")
    public TechnicalTaskDto findById(@PathVariable Long id) {
        return technicalTaskService.findById(id);
    }

    @PostMapping
    public void createTask(@RequestBody TechnicalTaskDto dto) {
        technicalTaskService.save(dto);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TechnicalTaskDto dto) {
        technicalTaskService.save(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        technicalTaskService.delete(id);
    }


}
