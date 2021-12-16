package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.memo.MemoDto;
import nicomed.tms.projectplanner.dto.memo.MemoForListDto;
import nicomed.tms.projectplanner.entity.Memo;
import nicomed.tms.projectplanner.repository.MemoRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class MemoQualifier extends AbstractEntityQualifier<Memo, Long> {

    private final MemoRepository repository;

    @Override
    public JpaRepository<Memo, Long> getRepository() {
        return repository;
    }

    public Memo getBy(MemoDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Named("memoByForList")
    public Memo getByForList(MemoForListDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Named("memoById")
    public Memo getById(Long id) {
        return getEntity(id);
    }

    @Override
    protected Memo getEntity(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return getRepository().findById(id).orElseThrow(() -> new NoSuchElementException("memo"));
    }

}

