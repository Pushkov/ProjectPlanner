package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.MemoDto;
import nicomed.tms.projectplanner.entity.Memo;
import nicomed.tms.projectplanner.mapper.MemoMapper;
import nicomed.tms.projectplanner.repository.MemoRepository;
import nicomed.tms.projectplanner.services.MemoService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
@JpaImpl
public class MemoJpaServiceImpl extends AbstractJpaService<MemoDto, Memo, Long> implements MemoService {

    private final MemoRepository memoRepository;
    private final MemoMapper mapper;

    @Override
    public JpaRepository<Memo, Long> getRepository() {
        return memoRepository;
    }

    @Override
    public MemoDto mapToDto(Memo entity) {
        return mapToDto(entity);
    }

    @Override
    public Memo mapToEntity(MemoDto dto) {
        return mapToEntity(dto);
    }

    @Override
    public Class<Memo> getEntityClass() {
        return Memo.class;
    }
}
