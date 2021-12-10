package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.memo.MemoDto;
import nicomed.tms.projectplanner.dto.project.ProjectForListDto;
import nicomed.tms.projectplanner.entity.Memo;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.entity.TitleList;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.mapper.MemoMapper;
import nicomed.tms.projectplanner.repository.MemoRepository;
import nicomed.tms.projectplanner.repository.ProjectRepository;
import nicomed.tms.projectplanner.services.MemoService;
import nicomed.tms.projectplanner.services.TitleListService;
import nicomed.tms.projectplanner.services.WorkshopService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class MemoJpaServiceImpl extends AbstractJpaService<MemoDto, Memo, Long> implements MemoService {

    private final MemoRepository memoRepository;
    private final MemoMapper mapper;
    private final WorkshopService workshopService;
    private final ProjectRepository projectRepository;
    private final TitleListService titleListService;

    @Override
    public JpaRepository<Memo, Long> getRepository() {
        return memoRepository;
    }

    @Override
    public MemoDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        System.out.println("delete memo " + id);
        Memo memo = findEntityById(id);
        if (!Objects.isNull(memo.getTitleList())) {
            TitleList titleList = memo.getTitleList();
            titleList.getMemos().remove(memo);
        }
        if (!Objects.isNull(memo.getWorkshop())) {
            Workshop workshop = memo.getWorkshop();
            workshop.getMemos().remove(memo);
        }
        if (isNotEmpty(memo.getProjects())) {
            for (Project project : memo.getProjects()) {
                project.setMemo(null);
            }
        }
        memoRepository.deleteById(memo.getId());
    }

    @Transactional
    @Override
    public void save(MemoDto dto) {
        Memo memo = mapToEntity(dto);
        setTitleList(dto.getTitleListYear(), memo);
        setWorkshop(dto.getWorkshopId(), memo);
        setProjectsList(dto.getProjects(), memo);
        memoRepository.save(memo);
    }

    @Transactional
    @Override
    public void save(Long id, MemoDto dto) {
        Memo memo = findEntityById(id);
        mapper.mapToEntity(memo, dto);

        setTitleList(dto.getTitleListYear(), memo);
        setWorkshop(dto.getWorkshopId(), memo);
        setProjectsList(dto.getProjects(), memo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setTitleList(Integer titleListYear, Memo memo) {
        if (Objects.isNull(titleListYear)) {
            titleListYear = LocalDate.now().getYear();
        }
        TitleList titleList = titleListService.findEntityById(titleListYear);
        if (isNotEmpty(titleList.getMemos())) {
            titleList.getMemos().add(memo);
        } else {
            titleList.setMemos(asList(memo));
        }
        memo.setTitleList(titleList);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setWorkshop(Long id, Memo memo) {
        if (!Objects.isNull(id)) {
            Workshop workshop = workshopService.findEntityById(id);
            if (isNotEmpty(workshop.getMemos())) {
                workshop.getMemos().add(memo);
            } else {
                workshop.setMemos(asList(memo));
            }
            memo.setWorkshop(workshop);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setProjectsList(Collection<ProjectForListDto> projectDtos, Memo memo) {
        if (CollectionUtils.isNotEmpty(projectDtos)) {
            List<Long> projectIds = projectDtos.stream()
                    .map(ProjectForListDto::getId)
                    .collect(Collectors.toList());
            List<Project> projects = projectRepository.findAllById(projectIds);
            for (Project project : projects) {
                project.setMemo(memo);
            }
            memo.setProjects(projects);
        }
    }

    @Override
    public Collection<MemoDto> findAll() {
        return super.findAll();
    }

    @Override
    public MemoDto mapToDto(Memo entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Memo mapToEntity(MemoDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<Memo> getEntityClass() {
        return Memo.class;
    }


}
