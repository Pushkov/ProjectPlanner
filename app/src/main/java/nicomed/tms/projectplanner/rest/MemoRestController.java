package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.memo.MemoDto;
import nicomed.tms.projectplanner.services.MemoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/memos")
public class MemoRestController {

    private final MemoService memoService;

    @GetMapping
    public Collection<MemoDto> findAll() {
        return memoService.findAll();
    }

    @GetMapping("/{id}")
    public MemoDto findById(@PathVariable Long id) {
        return memoService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateMemo(@PathVariable Long id, @RequestBody MemoDto dto) {
        memoService.save(id, dto);
    }

    @PostMapping
    public void create(@RequestBody @Valid MemoDto dto) {
        System.out.println("memo rest");
        memoService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memoService.delete(id);
    }


}
