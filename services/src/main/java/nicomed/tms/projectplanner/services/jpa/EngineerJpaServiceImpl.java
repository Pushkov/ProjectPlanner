package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.EngineerDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
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


@RequiredArgsConstructor
@JpaImpl
public class EngineerJpaServiceImpl<T extends BaseEntity<ID>, ID>
        extends AbstractJpaService<Engineer, Long>
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
    public List<EngineerDto> findAllListDto() {
        return mapper.mapToCollectionEngineerListDto((List<Engineer>) findAll());
    }

    @Override
    public EngineerDto findListDto(Long id) {
        return mapper.mapToEngineerListDto(findById(id));
    }

    @Override
    public List<EngineerDto> search(EngineerFilter engineerFilter) {
        Specification<Engineer> specification = EngineerSpecification.findByTerm(engineerFilter.getTerm());
        return mapper.mapToCollectionEngineerListDto(engineerRepository.findAll(specification));
    }
}
