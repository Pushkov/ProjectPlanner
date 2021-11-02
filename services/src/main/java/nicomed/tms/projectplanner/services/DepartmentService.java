package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.DepartmentDto1;
import nicomed.tms.projectplanner.dto.DepartmentDtoSecond;
import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.entity.Department;

import java.util.List;

public interface DepartmentService extends CrudService<Department, Long> {

    List<Department> findAll();

    List<DepartmentDtoShort> findAllAsDtoShort();

    List<DepartmentDto1> findAllAsDto1();

    List<DepartmentDtoSecond> findAllAsDtoSecond();

    Integer countAllByBasicDepartmentId(Long id);

    List<Department> findAllByBasicDepartmentId(Long id);
}