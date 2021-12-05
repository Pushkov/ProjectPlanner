package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.department.DepartmentStructureDto;

import java.util.List;

public interface DepartmentStructureService {

    List<DepartmentStructureDto> getStructure();

}