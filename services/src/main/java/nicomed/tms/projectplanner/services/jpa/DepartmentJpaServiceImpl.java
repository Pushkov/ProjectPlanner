package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.DepartmentJavaDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static nicomed.tms.projectplanner.mapper.DepartmentMapper.INSTANCE;

@RequiredArgsConstructor
@Service
public class DepartmentJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Department, Long> implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public JpaRepository<Department, Long> getRepository() {
        return departmentRepository;
    }

    @Override
    public DepartmentJavaDto findJavaDto(Long id) {
        return INSTANCE.mapToJavaDto(findById(id));
    }

    @Override
    public List<DepartmentJavaDto> findAllJavaDto() {
        return INSTANCE.mapToListJavaDto((List<Department>) findAll());
    }
}
