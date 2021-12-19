package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EngineerQualifier extends AbstractEntityQualifier<Engineer, Long> {

    private final EngineerRepository repository;

    @Override
    public JpaRepository<Engineer, Long> getRepository() {
        return repository;
    }

    @Named("engineerBy")
    public Engineer getBy(EngineerDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Named("engineerById")
    public Engineer getById(Long id) {
        return getEntity(id);
    }
}

