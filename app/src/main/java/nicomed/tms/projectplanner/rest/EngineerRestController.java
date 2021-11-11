package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.EngineerDto;
import nicomed.tms.projectplanner.services.EngineerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/")
public class EngineerRestController {

    private final EngineerService engineerService;

    @GetMapping("engineers")
    public List<EngineerDto> findAllListDto() {
        return engineerService.findAllListDto();
    }

    @GetMapping("engineers/{id}")
    public EngineerDto findListDtoById(@PathVariable Long id) {
        return engineerService.findListDto(id);
    }
}
