package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import nicomed.tms.projectplanner.services.DocumentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("documents/find/{id}")
    public DocumentDto getDocumentsDtoById(@PathVariable Long id) {
        return documentService.findDtoById(id);
    }

    @GetMapping("documents/search")
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
