package nicomed.tms.projectplanner.rest;


import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.DocumentSignedSimpleDto;
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
    public List<DocumentSignedSimpleDto> findALl() {
//        return documentBasicService.f();
        return null;
    }

    @GetMapping("/count")
    public Long findcountALl() {
        return documentBasicService.count();
    }


}
