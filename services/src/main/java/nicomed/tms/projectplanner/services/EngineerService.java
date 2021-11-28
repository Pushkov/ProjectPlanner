package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;

import java.util.List;

public interface EngineerService extends CrudService<EngineerDto, Long> {

    void save(Long id, EngineerDto dto);

    void setStatus(Long id, String status);

    default List<EngineerDto> search(EngineerFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }
}
