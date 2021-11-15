package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.TechnicalTaskDtoShort;
import nicomed.tms.projectplanner.entity.TechnicalTask;

import java.util.List;

public interface TechnicalTaskService extends CrudService<TechnicalTask, Long> {

    TechnicalTaskDtoShort findDtoShortById(Long id);

    List<TechnicalTaskDtoShort> findAllDtoShort();
}
