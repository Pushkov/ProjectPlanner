package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.dto.document.format.FormatDto;
import nicomed.tms.projectplanner.mapper.FormatMapper;
import nicomed.tms.projectplanner.services.DocumentFormatService;
import nicomed.tms.projectplanner.services.SheetFormatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/formats")
public class DocumentFormatRestController {

    private final DocumentFormatService documentFormatService;
    private final SheetFormatService sheetFormatService;
    private final FormatMapper mapper;

    @GetMapping
    public Collection<FormatDto> findAll() {
        return sheetFormatService.findAll();
    }

    @GetMapping("/document/{id}")
    public Collection<DocumentFormatDto> findAllByDocumentId(@PathVariable Long id) {
        return documentFormatService.findByDocumentId(id);
    }

}
