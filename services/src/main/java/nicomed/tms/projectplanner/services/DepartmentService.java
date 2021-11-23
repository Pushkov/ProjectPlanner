package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;

import java.util.List;

public interface DepartmentService extends CrudDoubleDtoService<DepartmentDto, DepartmentSimpleDto, Long> {

    DepartmentDto findByName(String name);

    Integer countAllByBasicDepartmentId(Long id);

    default List<DepartmentSimpleDto> search(DepartmentFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }

}