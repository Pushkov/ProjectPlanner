package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.EngineerDto;
import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;

import java.util.List;

public interface EngineerService extends CrudService<Engineer, Long> {

    EngineerJavaDto findJavaDtoByID(long id);

    List<EngineerJavaDto> findAllJavaDto();

    List<EngineerDto> findAllListDto();

    EngineerDto findListDto(Long id);

    default List<EngineerDto> search(EngineerFilter engineerFilter) {
        throw new UnsupportedOperationException();
    }

}
