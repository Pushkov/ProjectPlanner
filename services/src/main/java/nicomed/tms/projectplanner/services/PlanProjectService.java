package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;
import nicomed.tms.projectplanner.repository.specification.filter.PlanProjectFilter;

import java.util.List;

public interface PlanProjectService {

    PlanProjectDto findById(Long id);

    List<PlanProjectDto> search(PlanProjectFilter filter);

    List<PlanProjectDto> findAll();

    List<Integer> findAllYears();

    List<Integer> findAllMonths();

    List<String> findAllDepartmentNames();
}
