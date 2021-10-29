package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.entity.Department;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public interface DepartmentService {

    List<Department> findAll();
}