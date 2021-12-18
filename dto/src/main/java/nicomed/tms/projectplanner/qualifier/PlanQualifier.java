package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.entity.PlanPK;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.repository.PlanRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class PlanQualifier extends AbstractEntityQualifier<Plan, PlanPK> {

    private final PlanRepository repository;
    private final DepartmentRepository departmentRepository;

    @Override
    public JpaRepository<Plan, PlanPK> getRepository() {
        return repository;
    }

    public Plan getBy(PlanDto dto) {
        PlanPK id = PlanPK.builder()
                .year(dto.getYear())
                .month(dto.getMonth())
                .department(getDepartment(dto.getDepartmentId()))
                .build();

        return dto != null ? getEntity(id) : null;
    }

    private Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Dep not found"));
    }
}

