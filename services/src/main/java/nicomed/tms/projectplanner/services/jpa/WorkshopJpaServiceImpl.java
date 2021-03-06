package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.mapper.WorkshopMapper;
import nicomed.tms.projectplanner.repository.WorkshopRepository;
import nicomed.tms.projectplanner.services.WorkshopService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class WorkshopJpaServiceImpl extends AbstractJpaService<WorkshopDto, Workshop, Long> implements WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final WorkshopMapper mapper;

    @Override
    public JpaRepository<Workshop, Long> getRepository() {
        return workshopRepository;
    }

    @Override
    public WorkshopDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public void save(Long id, WorkshopDto dto) {
        Workshop workshop = findEntityById(id);
        mapper.mapToEntity(workshop, dto);
    }

    @Override
    public Collection<Workshop> findAllEntitesById(Iterable<Long> ids) {
        return workshopRepository.findAllById(ids);
    }

    @Override
    public WorkshopDto mapToDto(Workshop entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Workshop mapToEntity(WorkshopDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<Workshop> getEntityClass() {
        return Workshop.class;
    }

}
