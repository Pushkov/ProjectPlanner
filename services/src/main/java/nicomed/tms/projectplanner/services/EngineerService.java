package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.dto.EngineerListDto;
import nicomed.tms.projectplanner.entity.Engineer;

import java.util.List;

public interface EngineerService extends CrudService<Engineer, Long> {

    EngineerJavaDto findJavaDtoByID(long id);

    List<EngineerJavaDto> findAllJavaDto();

    List<EngineerListDto> findAllListDto();

    EngineerListDto findListDto(Long id);
}
