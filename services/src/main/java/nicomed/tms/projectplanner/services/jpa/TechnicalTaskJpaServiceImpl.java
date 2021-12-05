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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void save(Long id, TechnicalTaskDto dto) {
        TechnicalTask task = findEntityById(id);
        mapper.mapToEntity(task, dto);
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
