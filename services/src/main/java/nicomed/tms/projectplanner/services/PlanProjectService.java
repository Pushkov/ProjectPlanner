package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;

import java.util.Collection;
import java.util.List;

public interface PlanProjectService {

    PlanProjectDto findById(Long id);

    Collection<PlanProjectDto> search(PlanProjectDto dtoExample);

    Collection<PlanProjectDto> findAll();

    List<Integer> findAllYears();

    List<Integer> findAllMonths();

    List<String> findAllDepartmentNames();
}
