package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.TitleList;
import nicomed.tms.projectplanner.repository.TitleListRepository;
import nicomed.tms.projectplanner.services.TitleListService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TitleListJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<TitleList, Integer> implements TitleListService {

    private final TitleListRepository titleListRepository;

    @Override
    public JpaRepository<TitleList, Integer> getRepository() {
        return titleListRepository;
    }
}
