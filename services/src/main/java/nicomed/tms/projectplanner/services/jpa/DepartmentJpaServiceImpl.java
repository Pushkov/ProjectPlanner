package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.DepartmentDto1;
import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.mapper.DepartmentMapper;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Department, Long> implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public JpaRepository<Department, Long> getRepository() {
        return departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<DepartmentDtoShort> findAllAsDtoShort() {
        return findAll().stream().map(DepartmentMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDto1> findAllAsDto1() {
        return findAll().stream().map(DepartmentMapper.INSTANCE::mapToDto1).collect(Collectors.toList());
    }
}
