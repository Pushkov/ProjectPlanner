package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DepartmentDto;
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
    public List<DepartmentDto> getAll(@RequestParam(required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            return (List<DepartmentDto>) departmentService.findAll();
        }
        DepartmentFilter filter = DepartmentFilter.builder()
                .term(name)
                .build();
        return departmentService.search(filter);
    }

    @GetMapping("/{id}")
    public DepartmentDto getDtoShortById(@PathVariable("id") Long id) {
        return departmentService.findById(id);
    }

    @GetMapping("/name/{name}")
    public DepartmentDto getDtoShortById(@PathVariable("name") String name) {
        return departmentService.findDtoShortByName(name);
    }

    @GetMapping("/count/{id}")
    public Integer getCountInnerDepartmentsById(@PathVariable("id") Long id) {
        return departmentService.countAllByBasicDepartmentId(id);
    }

}