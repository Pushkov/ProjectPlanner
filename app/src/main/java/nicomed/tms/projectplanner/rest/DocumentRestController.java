package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import nicomed.tms.projectplanner.services.DocumentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/documents")
public class DocumentRestController {

    private final DocumentService documentService;

    @GetMapping()
    public Collection<DocumentSimpleDto> getAll() {
        return documentService.findAll();
    }

    @GetMapping("/page")
    public Page<DocumentSimpleDto> findPage(@RequestParam Integer page, @RequestParam Integer offset) {
        return documentService.findPage(page, offset);
    }

    @GetMapping("/{id}")
    public DocumentDto findById(@PathVariable Long id) {
        return documentService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateDocument(@PathVariable Long id, @RequestBody @Valid DocumentCreateDto dto) {
        documentService.save(id, dto);
    }

    @PostMapping
    public void createDocument(@RequestBody @Valid DocumentCreateDto dto) {
        documentService.save(dto);
    }

    @PreAuthorize("hasAnyAuthority('DOC_VERIF')")
    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.delete(id);
    }

    @GetMapping("/search")
    public List<DocumentSimpleDto> search(@RequestParam(required = false) String term) {
        if (StringUtils.isEmpty(term)) {
            return null;
        }
        DocumentFilter filter = DocumentFilter.builder()
                .term(term)
                .build();
        return documentService.search(filter);
    }

    @GetMapping("/count")
    public Long getDocumentsCount() {
        return documentService.count();
    }
}
