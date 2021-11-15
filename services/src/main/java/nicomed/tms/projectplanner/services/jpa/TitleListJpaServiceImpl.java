package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.TitleListDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.TitleList;
import nicomed.tms.projectplanner.mapper.TitleListMapper;
import nicomed.tms.projectplanner.repository.TitleListRepository;
import nicomed.tms.projectplanner.services.TitleListService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@JpaImpl
public class TitleListJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<TitleList, Integer> implements TitleListService {

    private final TitleListRepository titleListRepository;
    private final TitleListMapper mapper;

    @Override
    public JpaRepository<TitleList, Integer> getRepository() {
        return titleListRepository;
    }

    @Override
    public TitleListDto findDtoById(Integer year) {
        return mapper.mapToDto(findById(year));
    }

    @Override
    public List<TitleListDto> findAllDto() {
        return mapper.mapToListDto((List<TitleList>) findAll());
    }
}
