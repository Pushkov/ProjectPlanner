package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.MemoDtoShort;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Memo;
import nicomed.tms.projectplanner.mapper.MemoMapper;
import nicomed.tms.projectplanner.repository.MemoRepository;
import nicomed.tms.projectplanner.services.MemoService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
@JpaImpl
public class MemoJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Memo, Long> implements MemoService {

    private final MemoRepository memoRepository;
    private final MemoMapper mapper;

    @Override
    public JpaRepository<Memo, Long> getRepository() {
        return memoRepository;
    }

    @Override
    public MemoDtoShort findDtoShortById(Long id) {
        return mapper.mapToDtoShort(findById(id));
    }

    @Override
    public List<MemoDtoShort> findAllDtoShort() {
        return mapper.mapToListDtoShort((List<Memo>) findAll());
    }
}
