package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.PlanPK;
import nicomed.tms.projectplanner.repository.specification.filter.PlanFilter;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface PlanService extends CrudService<PlanDto, PlanPK> {

    PlanDto findByIdFields(Integer year, Integer month, Long id);

    Collection<PlanDto> search(PlanFilter filter);

    Page<PlanDto> findPage(Integer page, Integer offset);
}
