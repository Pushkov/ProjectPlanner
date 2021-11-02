package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.DepartmentDtoSecond;
import nicomed.tms.projectplanner.dto.DepartmentDtoShort;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.mapper.DepartmentMapper;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import nicomed.tms.projectplanner.repository.ProjectRepository;
import nicomed.tms.projectplanner.repository.WorkshopRepository;
import nicomed.tms.projectplanner.services.DepartmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Department, Long> implements DepartmentService {

//    @Override
//    public List<DepartmentDto1> findAllAsDto1() {
//        return findAll().stream().map(DepartmentMapper.INSTANCE::mapToDto1).collect(Collectors.toList());
//    }

    private final DepartmentRepository departmentRepository;
    private final EngineerRepository engineerRepository;
    private final ProjectRepository projectRepository;
    private final WorkshopRepository workshopRepository;

    @Override
    public JpaRepository<Department, Long> getRepository() {
        return departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        List<Department> resultList = departmentRepository.findAll();
        for (int i = 0; i < resultList.size(); i++) {
            resultList.get(i).setDepartments(findAllByBasicDepartmentId(resultList.get(i).getId()));
        }
        return resultList;
    }

    @Override
    public List<DepartmentDtoShort> findAllAsDtoShort() {
        return findAll().stream().map(DepartmentMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Integer countAllByBasicDepartmentId(Long id) {
        return departmentRepository.countAllByBasicDepartmentId(id);
    }

    @Override
    public List<Department> findAllByBasicDepartmentId(Long id) {
        return departmentRepository.findAllByBasicDepartmentId(id);
    }

    @Override
    public List<DepartmentDtoSecond> findAllAsDtoSecond() {
        return findAll().stream().map(DepartmentMapper.INSTANCE::mapToDtoSecond).collect(Collectors.toList());
    }
}
