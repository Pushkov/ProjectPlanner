package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.TechnicalTaskDto;
import nicomed.tms.projectplanner.services.TechnicalTaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/")
public class TechnicalTaskRestController {

    private final TechnicalTaskService technicalTaskService;

    @GetMapping("task/{id}")
    public TechnicalTaskDto findDtoShortById(@PathVariable Long id) {
        return technicalTaskService.findById(id);
    }

    @GetMapping("task")
    public List<TechnicalTaskDto> findAllDtoShor() {
        return (List<TechnicalTaskDto>) technicalTaskService.findAll();
    }

}
