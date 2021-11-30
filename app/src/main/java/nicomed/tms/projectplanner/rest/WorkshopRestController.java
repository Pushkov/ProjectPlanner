package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.services.WorkshopService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/workshops")
public class WorkshopRestController {

    private final WorkshopService workshopService;

    @GetMapping
    public Collection<WorkshopDto> findAll() {
        return workshopService.findAll();
    }

    @GetMapping("/{id}")
    public WorkshopDto findById(@PathVariable Long id) {
        return workshopService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateWorkshop(@PathVariable Long id, @RequestBody WorkshopDto dto) {
        workshopService.save(id, dto);
    }

    @PostMapping
    public void createWorkshop(@RequestBody WorkshopDto dto) {
        workshopService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkshop(@PathVariable Long id) {
        workshopService.delete(id);
    }
}
