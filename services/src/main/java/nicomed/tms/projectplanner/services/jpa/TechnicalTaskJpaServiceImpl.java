package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import nicomed.tms.projectplanner.repository.TechnicalTaskRepository;
import nicomed.tms.projectplanner.services.TechnicalTaskService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class TechnicalTaskJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<TechnicalTask, Long> implements TechnicalTaskService {

    private final TechnicalTaskRepository technicalTaskRepository;

    @Override
    public TechnicalTask findById(Long id) {
        return technicalTaskRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(TechnicalTask entity) {
        technicalTaskRepository.save(entity);
    }

    @Override
    public Collection<TechnicalTask> findAll() {
        return technicalTaskRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        technicalTaskRepository.deleteById(id);
    }

    @Override
    public JpaRepository<TechnicalTask, Long> getRepository() {
        return technicalTaskRepository;
    }
}
