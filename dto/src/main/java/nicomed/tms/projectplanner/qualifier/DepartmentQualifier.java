package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.dto.department.DepartmentStructureDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DepartmentQualifier extends AbstractEntityQualifier<Department, Long> {

    private final DepartmentRepository departmentRepository;


    public Department getBySimple(DepartmentSimpleDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    public Department getBy(DepartmentDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    public Department getByStructure(DepartmentStructureDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Named("departmentById")
    public Department getById(Long id) {
        return getEntity(id);
    }

    @Override
    public JpaRepository<Department, Long> getRepository() {
        return departmentRepository;
    }
}
