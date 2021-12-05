package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.plan.PlanPointDto;
import nicomed.tms.projectplanner.entity.Plan;

import java.util.Collection;

public interface PlanPointService extends CrudService<PlanPointDto, Long> {

    Collection<PlanPointDto> findAllByPlan(Plan plan);
}
