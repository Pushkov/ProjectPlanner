package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.TitleListDto;
import nicomed.tms.projectplanner.entity.TitleList;
import nicomed.tms.projectplanner.repository.TitleListRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TitleListQualifier extends AbstractEntityQualifier<TitleList, Integer> {

    private final TitleListRepository repository;

    @Override
    public JpaRepository<TitleList, Integer> getRepository() {
        return repository;
    }

    @Named("titleListBy")
    public TitleList getBy(TitleListDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Named("titleListByYear")
    public TitleList getByYear(Integer year) {
        return getEntity(year);
    }
}

