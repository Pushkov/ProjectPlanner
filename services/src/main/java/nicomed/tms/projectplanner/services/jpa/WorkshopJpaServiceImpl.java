package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.repository.WorkshopRepository;
import nicomed.tms.projectplanner.services.WorkshopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class WorkshopJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Workshop, Long> implements WorkshopService {

    private final WorkshopRepository workshopRepository;

    @Override
    public Workshop findById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(Workshop entity) {
        workshopRepository.save(entity);
    }

    @Override
    public Collection<Workshop> findAll() {
        return workshopRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        workshopRepository.deleteById(id);
    }

    @Override
    public JpaRepository<Workshop, Long> getRepository() {
        return workshopRepository;
    }
}
