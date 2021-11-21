package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.DepartmentDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.mapper.DepartmentMapper;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.repository.specification.DepartmentSpecification;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;
import nicomed.tms.projectplanner.services.DepartmentService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@JpaImpl
public class DepartmentJpaServiceImpl extends AbstractJpaService<DepartmentDto, Department, Long> implements DepartmentService, SearcheableService<Department> {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;

    @Override
    public JpaRepository<Department, Long> getRepository() {
        return departmentRepository;
    }

    @Override
    public SearchableRepository<Department, ?> getSearchRepository() {
        return departmentRepository;
    }


    @Override
    public List<DepartmentDto> search(DepartmentFilter departmentFilter) {
        Specification<Department> specification = DepartmentSpecification.findByTerm(departmentFilter.getTerm());
        return departmentRepository.findAll(specification).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto findDtoShortByName(String name) {
        Department department = departmentRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("Department with name = '" + name + "' not found"));
        return mapper.mapToDto(department);
    }

    @Override
    public Integer countAllByBasicDepartmentId(Long id) {
        return departmentRepository.countAllByBasicDepartmentId(id);
    }

    @Override
    public DepartmentDto mapToDto(Department entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Department mapToEntity(DepartmentDto dto) {
        return mapper.mapToEntity(dto);
    }
}
