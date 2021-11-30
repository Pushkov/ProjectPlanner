package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;

public interface TechnicalTaskService extends CrudService<TechnicalTaskDto, Long> {

    void save(Long id, TechnicalTaskDto dto);
}
