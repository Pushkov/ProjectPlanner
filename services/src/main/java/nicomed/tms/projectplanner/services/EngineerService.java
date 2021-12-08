package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EngineerService extends CrudService<EngineerDto, Long> {

    Long count();

    Engineer findEntityById(Long id);

    void save(Long id, EngineerDto dto);

    void setStatus(Long id, String status);

    Page<EngineerDto> findPage(Integer page, Integer offset);

    default List<EngineerDto> search(EngineerFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }
}
