package nicomed.tms.projectplanner.rest;


import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.DocumentSignedDto;
import nicomed.tms.projectplanner.services.DocumentSignedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/docbasics")
public class DocumentSignedRestController {

    private final DocumentSignedService documentBasicService;

    @GetMapping("")
    public List<DocumentSignedDto> findALl() {
        return documentBasicService.findAllDto();
    }

    @GetMapping("/count")
    public Long findcountALl() {
        return documentBasicService.count();
    }


}
