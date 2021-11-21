package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.mapper.EngineerMapper;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import nicomed.tms.projectplanner.repository.specification.EngineerSpecification;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@JpaImpl
public class EngineerJpaServiceImpl
        extends AbstractJpaService<EngineerDto, Engineer, Long>
        implements EngineerService, SearcheableService<Engineer> {

    private final EngineerRepository engineerRepository;
    private final EngineerMapper mapper;

    @Override
    public JpaRepository<Engineer, Long> getRepository() {
        return engineerRepository;
    }

    @Override
    public SearchableRepository<Engineer, ?> getSearchRepository() {
        return engineerRepository;
    }

    @Override
    public List<EngineerDto> search(EngineerFilter engineerFilter) {
        Specification<Engineer> specification = EngineerSpecification.findByTerm(engineerFilter.getTerm());
        return engineerRepository.findAll(specification).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EngineerDto mapToDto(Engineer entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Engineer mapToEntity(EngineerDto dto) {
        return mapper.mapToEntity(dto);
    }
}
