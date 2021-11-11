package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.DepartmentJavaDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.mapper.DepartmentMapper;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.services.DepartmentService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@RequiredArgsConstructor
@JpaImpl
public class DepartmentJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Department, Long> implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;

    @Override
    public JpaRepository<Department, Long> getRepository() {
        return departmentRepository;
    }

    @Override
    public DepartmentJavaDto findJavaDto(Long id) {
        return mapper.mapToJavaDto(findById(id));
    }

    @Override
    public List<DepartmentJavaDto> findAllJavaDto() {
        return mapper.mapToListJavaDto((List<Department>) findAll());
    }
}
