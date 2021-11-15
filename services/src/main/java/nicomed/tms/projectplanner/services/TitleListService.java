package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.TitleListDto;
import nicomed.tms.projectplanner.entity.TitleList;

import java.util.List;

public interface TitleListService extends CrudService<TitleList, Integer> {

    TitleListDto findDtoById(Integer year);

    List<TitleListDto> findAllDto();
}
