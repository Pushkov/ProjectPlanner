package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import nicomed.tms.projectplanner.entity.TitleList;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.mapper.TechnicalTaskMapper;
import nicomed.tms.projectplanner.repository.TechnicalTaskRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.TechnicalTaskService;
import nicomed.tms.projectplanner.services.TitleListService;
import nicomed.tms.projectplanner.services.WorkshopService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class TechnicalTaskJpaServiceImpl extends AbstractJpaService<TechnicalTaskDto, TechnicalTask, Long> implements TechnicalTaskService, SearcheableService<TechnicalTask> {

    private final TechnicalTaskRepository technicalTaskRepository;
    private final TechnicalTaskMapper mapper;
    private final TitleListService titleListService;
    private final WorkshopService workshopService;

    @Override
    public JpaRepository<TechnicalTask, Long> getRepository() {
        return technicalTaskRepository;
    }

    @Override
    public SearchableRepository<TechnicalTask, ?> getSearchRepository() {
        return technicalTaskRepository;
    }

    @Override
    public TechnicalTaskDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Collection<TechnicalTaskDto> findAll() {
        return super.findAll();
    }

    @Transactional
    @Override
    public void save(TechnicalTaskDto dto) {
        TechnicalTask task = mapToEntity(dto);
        technicalTaskRepository.save(task);
        setTitleList(dto.getTitleListYear(), task);
        setWorkshop(dto.getWorkshopId(), task);
    }

    @Transactional
    @Override
    public void save(Long id, TechnicalTaskDto dto) {
        TechnicalTask task = findEntityById(id);
        mapper.mapToEntity(task, dto);
        setTitleList(dto.getTitleListYear(), task);
        setWorkshop(dto.getWorkshopId(), task);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setTitleList(Integer titleListYear, TechnicalTask task) {
        if (Objects.isNull(titleListYear)) {
            titleListYear = LocalDate.now().getYear();
        }
        TitleList titleList = titleListService.findEntityById(titleListYear);
        if (isNotEmpty(titleList.getMemos())) {
            titleList.getTechnicalTasks().add(task);
        } else {
            titleList.setTechnicalTasks(asList(task));
        }
        task.setTitleList(titleList);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void setWorkshop(Long workshopId, TechnicalTask task) {
        if (!Objects.isNull(workshopId)) {
            Workshop workshop = workshopService.findEntityById(workshopId);
            if (isNotEmpty(workshop.getMemos())) {
                workshop.getTechnicalTasks().add(task);
            } else {
                workshop.setTechnicalTasks(asList(task));
            }
            task.setWorkshop(workshop);
        }
    }

    @Override
    public void delete(Long id) {
        TechnicalTask task = findEntityById(id);
        if (!Objects.isNull(task.getTitleList())) {
            TitleList titleList = task.getTitleList();
            titleList.getTechnicalTasks().remove(task);
        }
        if (!Objects.isNull(task.getWorkshop())) {
            Workshop workshop = task.getWorkshop();
            workshop.getTechnicalTasks().remove(task);
        }
        if (isNotEmpty(task.getProjects())) {
            for (Project project : task.getProjects()) {
                project.setMemo(null);
            }
        }
        technicalTaskRepository.deleteById(task.getId());
    }

    @Override
    public TechnicalTaskDto mapToDto(TechnicalTask entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public TechnicalTask mapToEntity(TechnicalTaskDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<TechnicalTask> getEntityClass() {
        return TechnicalTask.class;
    }
}
