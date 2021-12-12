package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.TitleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TitleListRepository extends JpaRepository<TitleList, Integer> {

    @Query("SELECT DISTINCT id FROM TitleList ")
    List<Integer> findAllYears();

}
