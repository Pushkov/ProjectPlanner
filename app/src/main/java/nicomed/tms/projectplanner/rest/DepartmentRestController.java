package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DepartmentRestController {

    private final DepartmentService departmentService;

    @GetMapping("/api/department")
    public List<Department> getAll() {
        return departmentService.findAll();
//        return List.of();
    }
}
