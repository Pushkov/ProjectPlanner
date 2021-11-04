package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Memo;
import nicomed.tms.projectplanner.repository.MemoRepository;
import nicomed.tms.projectplanner.services.MemoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemoJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Memo, Long> implements MemoService {

    private final MemoRepository memoRepository;

    @Override
    public Memo findById(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(Memo memo) {
        memoRepository.save(memo);
    }

    @Override
    public Collection<Memo> findAll() {
        return memoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        memoRepository.deleteById(id);
    }

    @Override
    public JpaRepository<Memo, Long> getRepository() {
        return memoRepository;
    }
}
