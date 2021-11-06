package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.DepartmentJavaDto;
import nicomed.tms.projectplanner.entity.Department;

import java.util.List;

public interface DepartmentService extends CrudService<Department, Long> {

    DepartmentJavaDto findJavaDto(Long id);

    List<DepartmentJavaDto> findAllJavaDto();
}