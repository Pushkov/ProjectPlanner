package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.entity.Workshop;

import java.util.Collection;

public interface WorkshopService extends CrudService<WorkshopDto, Long> {

    void save(Long id, WorkshopDto dto);

    Collection<Workshop> findAllEntitesById(Iterable<Long> ids);

    Workshop findEntityById(Long id);
}
