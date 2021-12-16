package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskForListDto;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import nicomed.tms.projectplanner.repository.TechnicalTaskRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class TechnicalTaskQualifier extends AbstractEntityQualifier<TechnicalTask, Long> {

    private final TechnicalTaskRepository repository;

    @Override
    public JpaRepository<TechnicalTask, Long> getRepository() {
        return repository;
    }

    public TechnicalTask getBy(TechnicalTaskDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Named("taskByForList")
    public TechnicalTask getByForList(TechnicalTaskForListDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Override
    protected TechnicalTask getEntity(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return getRepository().findById(id).orElseThrow(() -> new NoSuchElementException("task"));
    }

}

