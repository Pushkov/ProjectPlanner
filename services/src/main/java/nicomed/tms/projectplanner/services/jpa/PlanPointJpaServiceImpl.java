package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.plan.PlanPointDto;
import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.entity.PlanPoint;
import nicomed.tms.projectplanner.mapper.PlanPointMapper;
import nicomed.tms.projectplanner.repository.PlanPointRepository;
import nicomed.tms.projectplanner.services.PlanPointService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class PlanPointJpaServiceImpl extends AbstractJpaService<PlanPointDto, PlanPoint, Long> implements PlanPointService {

    private final PlanPointRepository planPointRepository;
    private final PlanPointMapper mapper;

    @Override
    public JpaRepository<PlanPoint, Long> getRepository() {
        return planPointRepository;
    }

    @Override
    public PlanPointDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Collection<PlanPointDto> findAllByPlan(Plan plan) {
        return planPointRepository.findAlByPlan(plan).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<PlanPointDto> findAll() {
        return super.findAll();
    }

    @Override
    public PlanPointDto mapToDto(PlanPoint entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public PlanPoint mapToEntity(PlanPointDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<PlanPoint> getEntityClass() {
        return PlanPoint.class;
    }
}
