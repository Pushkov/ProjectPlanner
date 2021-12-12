package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.entity.Workshop;

public interface WorkshopService extends CrudService<WorkshopDto, Long> {

    void save(Long id, WorkshopDto dto);

    Workshop findEntityById(Long id);
}
