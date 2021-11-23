package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
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
    public List<DepartmentSimpleDto> getAll(@RequestParam(required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            return (List<DepartmentSimpleDto>) departmentService.findAll();
        }
        DepartmentFilter filter = DepartmentFilter.builder()
                .term(name)
                .build();
        return departmentService.search(filter);
    }

    @GetMapping("/{id}")
    public DepartmentSimpleDto getById(@PathVariable("id") Long id) {
        return departmentService.findById(id);
    }

    @GetMapping("/search")
    public DepartmentDto getByName(@RequestParam(required = false) String name) {
        return departmentService.findByName(name);
    }

    @GetMapping("/count/{id}")
    public Integer getCountInnerDepartmentsById(@PathVariable("id") Long id) {
        return departmentService.countAllByBasicDepartmentId(id);
    }

}