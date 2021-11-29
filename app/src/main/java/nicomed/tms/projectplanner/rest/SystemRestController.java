package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.system.SystemLogDto;
import nicomed.tms.projectplanner.dto.system.SystemOptionDto;
import nicomed.tms.system.services.SystemLogService;
import nicomed.tms.system.services.SystemOptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/api/v1")
public class SystemRestController {

    private final SystemOptionService systemOptionService;
    private final SystemLogService systemLogService;

    @GetMapping("/options")
    public List<SystemOptionDto> getAllSystemOptions() {
        return systemOptionService.findAll();
    }

    @PostMapping("/options")
    public void saveSystemOption(@RequestBody SystemOptionDto dto) {
        systemOptionService.save(dto);
    }

    @PutMapping("/options")
    public void saveOption(@RequestBody SystemOptionDto option) {
        systemOptionService.save(option);
    }

    @GetMapping("/logs")
    public List<SystemLogDto> getAllSystemLogs() {
        return systemLogService.findAll();
    }
}
