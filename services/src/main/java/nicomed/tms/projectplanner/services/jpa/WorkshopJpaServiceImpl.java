package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.mapper.WorkshopMapper;
import nicomed.tms.projectplanner.repository.WorkshopRepository;
import nicomed.tms.projectplanner.services.WorkshopService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

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
    public WorkshopDto mapToDto(Workshop entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Workshop mapToEntity(WorkshopDto dto) {
        return mapper.mapToEntity(dto);
    }
}
