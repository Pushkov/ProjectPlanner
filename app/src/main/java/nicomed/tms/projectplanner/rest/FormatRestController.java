package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.format.FormatDto;
import nicomed.tms.projectplanner.services.SheetFormatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/formats")
public class FormatRestController {

    private final SheetFormatService sheetFormatService;

    @GetMapping
    public List<FormatDto> findAll() {
        return sheetFormatService.findAll();
    }

    @GetMapping("/{formatName}")
    public FormatDto findByFormatName(@PathVariable String formatName) {
        return sheetFormatService.findByFormat(formatName);
    }
}
