package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.PlanPK;
import org.springframework.data.domain.Page;

public interface PlanService extends CrudService<PlanDto, PlanPK> {

    Page<PlanDto> findPage(Integer page, Integer offset);
}
