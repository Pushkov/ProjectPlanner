package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.department.DepartmentStructureDto;
import nicomed.tms.projectplanner.mapper.DepartmentMapper;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.services.DepartmentStructureService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class DepartmentStructureServiceImpl implements DepartmentStructureService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentStructureDto> getStructure() {
        return departmentRepository.findAllByBasicDepartment_Id(null).stream()
                .map(mapper::mapToStructureDto)
                .collect(Collectors.toList());
    }
}
