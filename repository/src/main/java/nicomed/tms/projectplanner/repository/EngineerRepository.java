package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.enums.Status;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngineerRepository extends JpaRepository<Engineer, Long>, SearchableRepository<Engineer, Long> {

    List<Engineer> findAllByFirstNameLikeOrLastNameLikeAndStatus(String firstName, String lastName, Status status);


}
