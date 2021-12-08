package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.services.DocumentSignedService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/documents")
public class DocumentSignedRestController {

    private final DocumentSignedService documentSignedService;

    @GetMapping("/signed")
    public Collection<DocumentSignedDto> getAllSigned() {
        return documentSignedService.findAllSigned();
    }

    @PutMapping("/{id}/signed")
    public void updateDocument(@PathVariable Long id, @RequestBody DocumentCreateDto dto) {
        documentSignedService.save(id, dto);
    }

    @PostMapping("/signed")
    public void createDocument(@RequestBody DocumentCreateDto dto) {
        documentSignedService.save(dto);
    }


}
