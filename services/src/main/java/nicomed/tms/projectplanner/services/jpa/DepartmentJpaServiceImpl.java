package nicomed.tms.projectplanner.services.jpa;

import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DepartmentJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Department, Long> implements DepartmentService {
    @Override
    public JpaRepository<Department, Long> getRepository() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Department> findAll() {
        return List.of(
        );
    }
}
