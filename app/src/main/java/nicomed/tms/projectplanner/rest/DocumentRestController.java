package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.services.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/")
public class DocumentRestController {

    private final DocumentService documentService;

    @GetMapping("documents")
    public List<DocumentDto> getAllDocumentsDto() {

        return documentService.findAllDto();
    }

    @GetMapping("documents/{id}")
    public DocumentDto getDocumentsDtoById(@PathVariable Long id) {
        return documentService.findDtoById(id);
    }
}
