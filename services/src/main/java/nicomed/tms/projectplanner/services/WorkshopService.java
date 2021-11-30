package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;

public interface WorkshopService extends CrudService<WorkshopDto, Long> {

    void save(Long id, WorkshopDto dto);
}
