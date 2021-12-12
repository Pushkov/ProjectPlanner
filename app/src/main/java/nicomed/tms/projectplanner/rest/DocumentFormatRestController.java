package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.services.DocumentFormatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/documents")
public class DocumentFormatRestController {

    private final DocumentFormatService documentFormatService;

    @GetMapping("/{id}/formats")
    public Collection<DocumentFormatDto> findAllByDocumentId(@PathVariable Long id) {
        return documentFormatService.findByDocumentId(id);
    }

}
