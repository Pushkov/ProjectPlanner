package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.TitleListDto;
import nicomed.tms.projectplanner.services.TitleListService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/")
public class TitleListRestController {

    private final TitleListService titleListService;

    @Transactional
    @GetMapping("titlelist")
    public List<TitleListDto> findAllTitleListDto() {
        return (List<TitleListDto>) titleListService.findAll();
    }

    @Transactional
    @GetMapping("titlelist/{year}")
    public TitleListDto findTitleListDtoByYear(@PathVariable Integer year) {
        return titleListService.findById(year);
    }

}
