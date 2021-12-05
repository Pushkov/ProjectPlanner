package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.dto.document.format.FormatDto;
import nicomed.tms.projectplanner.mapper.FormatMapper;
import nicomed.tms.projectplanner.repository.SheetFormatRepository;
import nicomed.tms.projectplanner.services.DocumentFormatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/documentformats")
public class DocumentFormatRestController {

    private final DocumentFormatService documentFormatService;
    private final SheetFormatRepository sheetFormatRepository;
    private final FormatMapper mapper;

    @GetMapping
    public Collection<DocumentFormatDto> findAll() {
        return documentFormatService.findAll();
    }

    @GetMapping("/formats")
    public List<FormatDto> findAllFormats() {
        return sheetFormatRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

}
