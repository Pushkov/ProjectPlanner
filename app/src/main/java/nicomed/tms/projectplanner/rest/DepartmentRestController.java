package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.dto.department.DepartmentStructureDto;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;
import nicomed.tms.projectplanner.services.DepartmentService;
import nicomed.tms.projectplanner.services.DepartmentStructureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/planner/api/v1/departments")
public class DepartmentRestController {

    private final DepartmentService departmentService;
    private final DepartmentStructureService departmentStructureService;

    @GetMapping
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
    public DepartmentDto getById(@PathVariable("id") Long id) {
        return departmentService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        departmentService.save(id, departmentDto);
    }

    @PostMapping
    public void createDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.save(departmentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }

    @GetMapping("/search")
    public DepartmentDto getByName(@RequestParam(required = false) String name) {
        return departmentService.findByName(name);
    }

    @GetMapping("/count/{id}")
    public Integer getCountInnerDepartmentsById(@PathVariable("id") Long id) {
        return departmentService.countAllByBasicDepartmentId(id);
    }

    @GetMapping("/structure")
    public List<DepartmentStructureDto> getStructure() {
        return departmentStructureService.getStructure();
    }

    @PutMapping("/{department_id}/engineers/{engineer_id}/add")
    public void addEngineer(@PathVariable Long department_id, @PathVariable Long engineer_id) {
        departmentService.addEngineerById(department_id, engineer_id);
    }

    @PutMapping("/{department_id}/engineers/{engineer_id}/remove")
    public void removeEngineer(@PathVariable Long department_id, @PathVariable Long engineer_id) {
        departmentService.removeEngineerById(department_id, engineer_id);
    }
}