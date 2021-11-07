package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.dto.EngineerListDto;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.services.EngineerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/tms/planner/api/v1/")
public class EngineerRestController {

    private final EngineerService engineerService;

    @GetMapping("engineers-list")
    public List<EngineerListDto> findAllListDto() {
        return engineerService.findAllListDto();
    }

    @GetMapping("engineers-list/{id}")
    public EngineerListDto findListDtoById(@PathVariable Long id) {
        return engineerService.findListDto(id);
    }


    @GetMapping("engineers")
    public List<Engineer> findAll() {
        return (List<Engineer>) engineerService.findAll();
    }

    @GetMapping("engineers-dto/java")
    public List<EngineerJavaDto> findAllJavaDto() {
        return engineerService.findAllJavaDto();
    }


    @GetMapping("engineers/{id}")
    public Engineer findById(@PathVariable Long id) {
        return engineerService.findById(id);
    }

    @GetMapping("engineers-dto/java/{id}")
    public EngineerJavaDto findJavaDtoById(@PathVariable Long id) {
        return engineerService.findJavaDtoByID(id);
    }
}
