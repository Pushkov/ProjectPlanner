package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;

import java.util.List;

public interface DepartmentService extends CrudDoubleDtoService<DepartmentDto, DepartmentSimpleDto, Long> {

    void save(Long id, DepartmentDto departmentDto);

    Department findEntityById(Long id);

    DepartmentDto findByName(String name);

    Integer countAllByBasicDepartmentId(Long id);

    void addEngineerById(Long departmentId, Long engineerId);

    void removeEngineerById(Long departmentId, Long engineerId);

    default List<DepartmentSimpleDto> search(DepartmentFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }

}