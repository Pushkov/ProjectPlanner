package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import nicomed.tms.projectplanner.services.EngineerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/engineers")
public class EngineerRestController {

    private final EngineerService engineerService;

    @GetMapping
    public Collection<EngineerDto> findAll() {
        return engineerService.findAll();
    }

    @GetMapping("/page")
    public Page<EngineerDto> findPage(@RequestParam Integer page, @RequestParam Integer offset) {
        return engineerService.findPage(page, offset);
    }

    @GetMapping("/{id}")
    public EngineerDto findById(@PathVariable Long id) {
        return engineerService.findById(id);
    }

    @PostMapping
    public void createEngineer(@Valid @RequestBody EngineerDto engineerDto) {
        engineerService.save(engineerDto);
    }

    @PutMapping("/{id}")
    public void updateEngineer(@PathVariable Long id, @Valid @RequestBody EngineerDto engineerDto) {
        engineerService.save(id, engineerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEngineer(@PathVariable Long id) {
        engineerService.delete(id);
    }

    @GetMapping("/{id}/status")
    public void setStatus(@PathVariable Long id, @RequestParam String status) {
        engineerService.setStatus(id, status);
    }

    @GetMapping("/search")
    public List<EngineerDto> search(@RequestParam(required = false) String term) {
        if (StringUtils.isEmpty(term)) {
            return null;
        }
        EngineerFilter filter = EngineerFilter.builder()
                .term(term)
                .build();
        return engineerService.search(filter);
    }

    @GetMapping("/search/last-name")
    public Collection<EngineerDto> lastNameStartWith(@RequestParam(required = false) String term) {
        if (StringUtils.isEmpty(term)) {
            return null;
        }
        EngineerFilter filter = EngineerFilter.builder()
                .term(term)
                .build();
        return engineerService.findAllByLastNameStartWith(filter);
    }

    @GetMapping("/count")
    public Long count() {
        return engineerService.count();
    }
}
