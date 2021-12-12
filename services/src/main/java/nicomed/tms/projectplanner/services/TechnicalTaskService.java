package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;
import nicomed.tms.projectplanner.entity.TechnicalTask;

public interface TechnicalTaskService extends CrudService<TechnicalTaskDto, Long> {

    TechnicalTask findEntityById(Long id);

    void save(Long id, TechnicalTaskDto dto);
}
