package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.EngineerDto;
import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.mapper.EngineerMapper;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@RequiredArgsConstructor
@JpaImpl
public class EngineerJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Engineer, Long> implements EngineerService {

    private final EngineerRepository engineerRepository;
    private final EngineerMapper mapper;

    @Override
    public JpaRepository<Engineer, Long> getRepository() {
        return engineerRepository;
    }

    @Override
    public EngineerJavaDto findJavaDtoByID(long id) {
        return mapper.mapToJavaDto(findById(id));
    }

    @Override
    public List<EngineerJavaDto> findAllJavaDto() {
        return mapper.mapToListJavaDto((List<Engineer>) findAll());
    }

    @Override
    public List<EngineerDto> findAllListDto() {
        return mapper.mapToCollectionEngineerListDto((List<Engineer>) findAll());
    }

    @Override
    public EngineerDto findListDto(Long id) {
        return mapper.mapToEngineerListDto(findById(id));
    }
}
