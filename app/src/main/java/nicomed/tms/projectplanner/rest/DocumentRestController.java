package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.document.DocumentDtoShort;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import nicomed.tms.projectplanner.services.DocumentService;
import nicomed.tms.projectplanner.services.DocumentSignedService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/documents")
public class DocumentRestController {

    private final DocumentService documentService;
    private final DocumentSignedService documentSignedService;

    @GetMapping("")
    public List<DocumentDto> getAllDocumentsDto() {
        return (List<DocumentDto>) documentService.findAll();
    }

    @GetMapping("/{id}")
    public DocumentDtoShort getDocumentsDtoById(@PathVariable Long id) {
        return documentService.findById(id);
    }

    @Transactional
    @GetMapping("/{id}/signed")
    public DocumentSignedDto getDocumentSignedById(@PathVariable Long id) {
        return documentSignedService.findById(id);
    }


    @GetMapping("/search")
    public List<DocumentDto> search(@RequestParam(required = false) String term) {
        if (StringUtils.isEmpty(term)) {
            return null;
        }
        DocumentFilter filter = DocumentFilter.builder()
                .term(term)
                .build();
        return documentService.search(filter);
    }
}
