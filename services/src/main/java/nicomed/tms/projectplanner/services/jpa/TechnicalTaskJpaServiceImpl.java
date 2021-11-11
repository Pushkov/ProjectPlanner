package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.TechnicalTask;
import nicomed.tms.projectplanner.repository.TechnicalTaskRepository;
import nicomed.tms.projectplanner.services.TechnicalTaskService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
@JpaImpl
public class TechnicalTaskJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<TechnicalTask, Long> implements TechnicalTaskService {

    private final TechnicalTaskRepository technicalTaskRepository;

    @Override
    public JpaRepository<TechnicalTask, Long> getRepository() {
        return technicalTaskRepository;
    }
}
