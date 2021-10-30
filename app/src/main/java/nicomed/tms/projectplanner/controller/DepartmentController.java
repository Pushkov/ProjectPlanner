package nicomed.tms.projectplanner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public String getAll() {
        return Arrays.toString(departmentService.findAll().toArray());
    }
}