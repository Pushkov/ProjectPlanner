package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    @GetMapping("departments")
    public List<Department> getAllEntity() {
        return (List<Department>) departmentService.findAll();
    }

    @GetMapping("departments-dto/java")
    public List<DepartmentJavaDto> getAllJavaDto() {
        return departmentService.findAllJavaDto();
    }

    @GetMapping("departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long id) {
        return departmentService.findById(id);
    }

    @GetMapping("departments-dto/java/{id}")
    public DepartmentJavaDto getDepartmentJavaDtoById(@PathVariable("id") Long id) {
        return departmentService.findJavaDto(id);
    }
}