package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.repository.WorkshopRepository;
import nicomed.tms.projectplanner.services.WorkshopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkshopJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Workshop, Long> implements WorkshopService {

    private final WorkshopRepository workshopRepository;

    @Override
    public JpaRepository<Workshop, Long> getRepository() {
        return workshopRepository;
    }
}
