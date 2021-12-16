package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import nicomed.tms.projectplanner.mapper.TechnicalTaskMapper;
import nicomed.tms.projectplanner.repository.TechnicalTaskRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.TechnicalTaskService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class TechnicalTaskJpaServiceImpl extends AbstractJpaService<TechnicalTaskDto, TechnicalTask, Long> implements TechnicalTaskService, SearcheableService<TechnicalTask> {

    private final TechnicalTaskRepository technicalTaskRepository;
    private final TechnicalTaskMapper mapper;

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
        addExtensions(task);
    }

    @Transactional
    @Override
    public void save(Long id, TechnicalTaskDto dto) {
        TechnicalTask task = findEntityById(id);
        mapper.mapToEntity(task, dto);
        task.getBaseTask().getExtensions().add(task);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void addExtensions(TechnicalTask task) {
        TechnicalTask baseTask = task.getBaseTask();
        if (!Objects.isNull(baseTask)) {
            List<TechnicalTask> extensions = baseTask.getExtensions();
            if (CollectionUtils.isNotEmpty(extensions)) {
                extensions.add(task);
            } else {
                baseTask.setExtensions(asList(task));
            }
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        TechnicalTask task = findEntityById(id);
        removeExtensions(task);
        technicalTaskRepository.deleteById(task.getId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void removeExtensions(TechnicalTask task) {
        TechnicalTask baseTask = task.getBaseTask();
        if (!Objects.isNull(baseTask)) {
            List<TechnicalTask> extensions = baseTask.getExtensions();
            if (CollectionUtils.isNotEmpty(extensions)) {
                extensions.remove(task);
            }
        }
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
