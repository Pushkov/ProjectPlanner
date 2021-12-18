package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EngineerService extends CrudService<EngineerDto, Long> {

    Long count();

    Optional<Engineer> findByLogin(String login);

    Engineer findEntityById(Long id);

    void save(Long id, EngineerDto dto);

    void setStatus(Long id, String status);

    Page<EngineerDto> findPage(Integer page, Integer offset);

    EngineerDto findByLastName(String lastName);

    Collection<EngineerDto> findAllByLastNameStartWith(EngineerFilter engineerFilter);

    default List<EngineerDto> search(EngineerFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }
}
