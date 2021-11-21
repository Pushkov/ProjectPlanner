package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.DepartmentDto;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;

import java.util.List;

public interface DepartmentService extends CrudService<DepartmentDto, Long> {

    DepartmentDto findDtoShortByName(String name);

    Integer countAllByBasicDepartmentId(Long id);

    default List<DepartmentDto> search(DepartmentFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }

}