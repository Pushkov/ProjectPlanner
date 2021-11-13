package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.system.entity.SystemOption;
import nicomed.tms.system.repository.SystemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/api/v1/")
public class SystemRestController {

    private final SystemRepository systemRepository;

    @GetMapping("")
    public List<SystemOption> getAllSystemptions() {
        return systemRepository.findAll();
    }
}
