package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.memo.MemoDto;
import nicomed.tms.projectplanner.entity.Memo;

public interface MemoService extends CrudService<MemoDto, Long> {

    void save(Long id, MemoDto dto);

    Memo findEntityById(Long id);

}
