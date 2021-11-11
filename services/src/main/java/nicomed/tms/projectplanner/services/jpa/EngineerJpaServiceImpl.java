package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.dto.EngineerListDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.mapper.EngineerMapper;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import nicomed.tms.projectplanner.services.EngineerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
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
    public List<EngineerListDto> findAllListDto() {
        return mapper.mapToCollectionEngineerListDto((List<Engineer>) findAll());
    }

    @Override
    public EngineerListDto findListDto(Long id) {
        return mapper.mapToEngineerListDto(findById(id));
    }
}
