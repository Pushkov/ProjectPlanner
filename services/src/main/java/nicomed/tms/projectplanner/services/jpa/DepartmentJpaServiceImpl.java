package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.department.DepartmentDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.dto.workshop.WorkshopSimpleDto;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.mapper.DepartmentMapper;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.repository.specification.DepartmentSpecification;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;
import nicomed.tms.projectplanner.services.DepartmentService;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.WorkshopService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.throwNotFoundByIdException;
import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.throwNotFoundByNameException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class DepartmentJpaServiceImpl extends AbstractDoubleDtoJpaService<DepartmentDto, DepartmentSimpleDto, Department, Long> implements DepartmentService, SearcheableService<Department> {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;
    private final EngineerService engineerService;
    private final WorkshopService workshopService;

    @Override
    public JpaRepository<Department, Long> getRepository() {
        return departmentRepository;
    }

    @Override
    public SearchableRepository<Department, ?> getSearchRepository() {
        return departmentRepository;
    }

    @Override
    public DepartmentDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Department findEntityById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> throwNotFoundByIdException(getEntityClass(), id));
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

    @Transactional
    @Override
    public void save(DepartmentDto dto) {
        Department department = mapToEntity(dto);
        setBasicDepartment(dto.getBasicDepId(), department);
        serWorkshops(dto.getWorkshops(), department);
        departmentRepository.save(department);
    }

    @Transactional
    @Override
    public void save(Long id, DepartmentDto dto) {
        Department department = findEntityById(id);
        setBasicDepartment(dto.getBasicDepId(), department);
        serWorkshops(dto.getWorkshops(), department);
        mapper.mapToEntity(department, dto);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setBasicDepartment(Long basicId, Department department) {
        if (!Objects.isNull(basicId)) {
            Department basicDepartment = findEntityById(basicId);
            department.setBasicDepartment(basicDepartment);
        } else {
            department.setBasicDepartment(null);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void serWorkshops(List<WorkshopSimpleDto> workshopSimpleDtos, Department department) {
        if (!CollectionUtils.isEmpty(workshopSimpleDtos)) {
            List<Long> workshopsIdList = workshopSimpleDtos.stream()
                    .map(WorkshopSimpleDto::getId)
                    .collect(Collectors.toList());
            Collection<Workshop> workshops = workshopService.findAllEntitesById(workshopsIdList);
            for (Workshop workshop : workshops) {
                workshop.setDepartment(department);
            }
            department.setWorkshops((List<Workshop>) workshops);
        }
    }

    @Transactional
    @Override
    public void addEngineerById(Long departmentId, Long engineerId) {
        if (!Objects.isNull(departmentId) && !Objects.isNull(engineerId)) {
            addEngineer(findEntityById(departmentId),
                    engineerService.findEntityById(engineerId));
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void addEngineer(Department department, Engineer engineer) {
        List<Engineer> engineers = department.getEngineers();
        if (engineers != null) {
            engineers.add(engineer);
        } else {
            department.setEngineers(asList(engineer));
        }
        engineer.setDepartment(department);
    }

    @Transactional
    @Override
    public void removeEngineerById(Long departmentId, Long engineerId) {
        if (!Objects.isNull(departmentId) && !Objects.isNull(engineerId)) {
            removeEngineer(findEntityById(departmentId),
                    engineerService.findEntityById(engineerId));
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void removeEngineer(Department department, Engineer engineer) {
        List<Engineer> engineers = department.getEngineers();
        if (!CollectionUtils.isEmpty(engineers)) {
            engineers.remove(engineer);
        }
        engineer.setDepartment(null);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Department department = findEntityById(id);
        Department basicDep = department.getBasicDepartment();
        if (!Objects.isNull(basicDep)) {
            basicDep.getDepartments().remove(department);
            department.setBasicDepartment(null);
        }
        clearEngineers(department);
        clearProjects(department);
        clearWorkshops(department);
        departmentRepository.delete(department);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void clearEngineers(Department department) {
        List<Engineer> engineers = department.getEngineers();
        if (!CollectionUtils.isEmpty(engineers)) {
            for (Engineer engineer : engineers) {
                engineer.setDepartment(null);
            }
            department.getEngineers().clear();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void clearProjects(Department department) {
        List<Project> projects = department.getProjects();
        if (!CollectionUtils.isEmpty(projects)) {
            for (Project project : projects) {
                project.setDepartment(null);
            }
            department.getProjects().clear();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void clearWorkshops(Department department) {
        List<Workshop> workshops = department.getWorkshops();
        if (!CollectionUtils.isEmpty(workshops)) {
            for (Workshop workshop : workshops) {
                workshop.setDepartment(null);
            }
            department.getWorkshops().clear();
        }
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