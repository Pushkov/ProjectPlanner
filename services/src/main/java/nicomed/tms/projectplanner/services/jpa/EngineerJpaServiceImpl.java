package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.EngineerJavaDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import nicomed.tms.projectplanner.services.EngineerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static nicomed.tms.projectplanner.mapper.EngineerMapper.INSTANCE;

@RequiredArgsConstructor
@Service
public class EngineerJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Engineer, Long> implements EngineerService {

    private final EngineerRepository engineerRepository;

    @Override
    public JpaRepository<Engineer, Long> getRepository() {
        return engineerRepository;
    }

    @Override
    public EngineerJavaDto findJavaDtoByID(long id) {
        return INSTANCE.mapToJavaDto(findById(id));
    }

    @Override
    public List<EngineerJavaDto> findAllJavaDto() {
        return INSTANCE.mapToListJavaDto((List<Engineer>) findAll());
    }
}
