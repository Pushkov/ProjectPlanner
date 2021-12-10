package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.TitleListDto;
import nicomed.tms.projectplanner.services.TitleListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/titlelists")
public class TitleListRestController {

    private final TitleListService titleListService;

    @GetMapping
    public List<TitleListDto> findAll() {
        return (List<TitleListDto>) titleListService.findAll();
    }

    @GetMapping("/{year}")
    public TitleListDto findByYear(@PathVariable Integer year) {
        return titleListService.findById(year);
    }

    @GetMapping("/years")
    public List<Integer> findAllYears() {
        return titleListService.findAllYears();
    }

}
