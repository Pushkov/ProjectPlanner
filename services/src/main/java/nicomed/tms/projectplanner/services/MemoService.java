package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.MemoDtoShort;
import nicomed.tms.projectplanner.entity.Memo;

import java.util.List;

public interface MemoService extends CrudService<Memo, Long> {

    MemoDtoShort findDtoShortById(Long id);

    List<MemoDtoShort> findAllDtoShort();
}
