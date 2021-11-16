package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;

import java.util.List;

public interface DepartmentService extends CrudService<Department, Long> {

    DepartmentDtoShort findDtoShortById(Long id);

    DepartmentDtoShort findDtoShortByName(String name);

    List<DepartmentDtoShort> findAllDtoShort();

    Integer countAllByBasicDepartmentId(Long id);

    default List<DepartmentDtoShort> search(DepartmentFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }

}