package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.enums.Status;
import nicomed.tms.projectplanner.mapper.EngineerMapper;
import nicomed.tms.projectplanner.repository.AddressRepository;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import nicomed.tms.projectplanner.repository.RoleRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import nicomed.tms.projectplanner.services.DepartmentService;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.RoleService;
import nicomed.tms.projectplanner.services.aspect.LoggegMethod;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.enums.Status.valueOf;
import static nicomed.tms.projectplanner.repository.specification.EngineerSpecification.findByTerm;
import static nicomed.tms.projectplanner.services.exception.ExceptionHandler.throwNotFoundByIdException;


@RequiredArgsConstructor
@JpaImpl
public class EngineerJpaServiceImpl
        extends AbstractJpaService<EngineerDto, Engineer, Long>
        implements EngineerService, SearcheableService<Engineer> {

    private final EngineerRepository engineerRepository;
    private final EngineerMapper mapper;
    private final AddressRepository addressRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final DepartmentService departmentService;

    @Override
    public JpaRepository<Engineer, Long> getRepository() {
        return engineerRepository;
    }

    @Override
    public SearchableRepository<Engineer, ?> getSearchRepository() {
        return engineerRepository;
    }

    @Override
    public Long count() {
        return engineerRepository.count();
    }

    @Transactional
    @Override
    public void save(EngineerDto dto) {
        System.out.println("login dto: " + dto.getLogin());
        Engineer engineer = mapToEntity(dto);
        System.out.println("login eng: " + engineer.getLogin());
        engineer.setDepartment(getDepartment(dto.getDepartmentSimpleDto().getId()));
        engineer.setRole(getRole(dto.getRoleSimpleDto().getId()));
        engineerRepository.save(engineer);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> throwNotFoundByIdException(Department.class, id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected Role getRole(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> throwNotFoundByIdException(Role.class, id));
    }

    @Transactional
    @Override
    public void save(Long id, EngineerDto dto) {
        Engineer engineer = findEntityById(id);
        engineer.setRole(getRole(dto.getRoleSimpleDto().getId()));
        engineer.setDepartment(getDepartment(dto.getDepartmentSimpleDto().getId()));
        mapper.mapToEntity(engineer, dto);
    }

    @Transactional
    @Override
    public void delete(Long aLong) {
        super.delete(aLong);
    }

    @Override
    public List<EngineerDto> search(EngineerFilter engineerFilter) {
        Specification<Engineer> specification = findByTerm(engineerFilter.getTerm());
        return engineerRepository.findAll(specification).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @LoggegMethod(value = "Engineer id={id} set status '{statusName}'", activity = "engineer status")
    @Transactional
    @Override
    public void setStatus(Long id, String statusName) {
        Status status = valueOf(statusName);
        findEntityById(id).setStatus(status);
    }

    @Override
    public EngineerDto mapToDto(Engineer entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Engineer mapToEntity(EngineerDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<Engineer> getEntityClass() {
        return Engineer.class;
    }
}
