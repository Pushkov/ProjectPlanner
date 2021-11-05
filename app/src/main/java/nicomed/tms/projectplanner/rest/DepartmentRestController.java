package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DepartmentDtoSecond;
import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.dto.DepartmentJavaDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class DepartmentRestController {

    @GetMapping("api/deps3")
    public List<DepartmentJavaDto> getAll3() {
        return departmentService.findAllAsDto1();
    }

    private final DepartmentService departmentService;

    @GetMapping("api/deps")
    public List<Department> getAll1() {
        return departmentService.findAll();
    }

    @GetMapping("api/deps1")
    public List<DepartmentDtoShort> getAll() {
        return departmentService.findAllAsDtoShort();
    }

    @GetMapping("api/deps2")
    public List<DepartmentDtoSecond> getAll2() {
        return departmentService.findAllAsDtoSecond();
    }

    @GetMapping("api/deps/count/{id}")
    public Integer getCount1(@PathVariable("id") Long id) {
        return departmentService.countAllByBasicDepartmentId(id);
    }
}