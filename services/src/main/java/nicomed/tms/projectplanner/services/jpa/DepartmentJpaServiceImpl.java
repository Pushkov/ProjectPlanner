package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
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
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.services.exception.ExceptionHandler.throwNotFoundByNameException;

@RequiredArgsConstructor
@JpaImpl
public class DepartmentJpaServiceImpl extends AbstractDoubleDtoJpaService<DepartmentDto, DepartmentSimpleDto, Department, Long> implements DepartmentService, SearcheableService<Department> {

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
    public List<DepartmentSimpleDto> search(DepartmentFilter departmentFilter) {
        Specification<Department> specification = DepartmentSpecification.findByTerm(departmentFilter.getTerm());
        return departmentRepository.findAll(specification).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countAllByBasicDepartmentId(Long id) {
        return departmentRepository.countAllByBasicDepartmentId(id);
    }

    @Override
    public DepartmentDto findByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name).map(this::mapToDto)
                .orElseThrow(() -> throwNotFoundByNameException(getEntityClass(), name));
    }

    @Override
    public DepartmentSimpleDto mapToSimpleDto(Department entity) {
        return mapper.mapToSimpleDto(entity);
    }

    @Override
    public DepartmentDto mapToDto(Department entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Department mapToEntity(DepartmentDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<Department> getEntityClass() {
        return Department.class;
    }
}

