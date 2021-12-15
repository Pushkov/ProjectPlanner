package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class DepartmentQualifier {

    private final DepartmentRepository departmentRepository;

    public Department getBySimple(DepartmentSimpleDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        return getWorkshop(dto.getId());
    }

    public Workshop getBy(WorkshopDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        return getWorkshop(dto.getId());
    }

    private Department getDepartment(Long id) {
        if (id == null) {
            return null;
        }
        return departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
    }
}
