package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.TitleListDto;
import nicomed.tms.projectplanner.entity.TitleList;
import nicomed.tms.projectplanner.mapper.TitleListMapper;
import nicomed.tms.projectplanner.repository.TitleListRepository;
import nicomed.tms.projectplanner.services.TitleListService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class TitleListJpaServiceImpl extends AbstractJpaService<TitleListDto, TitleList, Integer> implements TitleListService {

    private final TitleListRepository titleListRepository;
    private final TitleListMapper mapper;

    @Override
    public JpaRepository<TitleList, Integer> getRepository() {
        return titleListRepository;
    }

    @Override
    public TitleListDto findById(Integer integer) {
        return super.findById(integer);
    }

    @Override
    public Collection<TitleListDto> findAll() {
        return super.findAll();
    }

    @Override
    public TitleListDto mapToDto(TitleList entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public TitleList mapToEntity(TitleListDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<TitleList> getEntityClass() {
        return TitleList.class;
    }
}
