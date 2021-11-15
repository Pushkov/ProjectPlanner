package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.TechnicalTaskDtoShort;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import nicomed.tms.projectplanner.mapper.TechnicalTaskMapper;
import nicomed.tms.projectplanner.repository.TechnicalTaskRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.TechnicalTaskService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
@JpaImpl
public class TechnicalTaskJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<TechnicalTask, Long> implements TechnicalTaskService, SearcheableService<TechnicalTask> {

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
    public TechnicalTaskDtoShort findDtoShortById(Long id) {
        return mapper.mapToDtoShort(findById(id));
    }

    @Override
    public List<TechnicalTaskDtoShort> findAllDtoShort() {
        return mapper.mapToListDtoShort((List<TechnicalTask>) findAll());
    }
}
