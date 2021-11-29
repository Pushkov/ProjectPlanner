package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.MemoDto;
import nicomed.tms.projectplanner.services.MemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/memos")
public class MemoRestController {

    private final MemoService memoService;

    @GetMapping
    public Collection<MemoDto> findAllDtoShort() {
        return memoService.findAll();
    }

    @GetMapping("/{id}")
    public MemoDto findDtoShortById(@PathVariable Long id) {
        return memoService.findById(id);
    }
}
