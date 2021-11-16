package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/departments")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    @GetMapping("")
    public List<DepartmentDtoShort> getAll(@RequestParam(required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            return departmentService.findAllDtoShort();
        }
        DepartmentFilter filter = DepartmentFilter.builder()
                .term(name)
                .build();
        return departmentService.search(filter);
    }

    @GetMapping("/{id}")
    public DepartmentDtoShort getDtoShortById(@PathVariable("id") Long id) {
        return departmentService.findDtoShortById(id);
    }

    @GetMapping("/name/{name}")
    public DepartmentDtoShort getDtoShortById(@PathVariable("name") String name) {
        return departmentService.findDtoShortByName(name);
    }

    @GetMapping("/count/{id}")
    public Integer getCountInnerDepartmentsById(@PathVariable("id") Long id) {
        return departmentService.countAllByBasicDepartmentId(id);
    }

}