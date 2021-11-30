package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.MemoDto;

public interface MemoService extends CrudService<MemoDto, Long> {

    void save(Long id, MemoDto dto);

}
