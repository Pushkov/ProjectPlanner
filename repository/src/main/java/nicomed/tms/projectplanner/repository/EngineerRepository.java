package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EngineerRepository extends JpaRepository<Engineer, Long>, JpaSpecificationExecutor<Engineer> {

    List<Engineer> findAllByFirstNameLikeOrLastNameLikeAndStatus(String firstName, String lastName, Status status);
}
