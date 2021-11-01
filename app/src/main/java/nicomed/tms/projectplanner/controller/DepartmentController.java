package nicomed.tms.projectplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DepartmentDto1;
import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("api/deps")
    public List<DepartmentDtoShort> getAll() {
        return departmentService.findAllAsDtoShort();
    }

    @GetMapping("api/deps1")
    public List<DepartmentDto1> getAll1() {
        return departmentService.findAllAsDto1();
    }

}