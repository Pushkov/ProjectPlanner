package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.enums.Status;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EngineerRepository extends JpaRepository<Engineer, Long>, SearchableRepository<Engineer, Long> {


    List<Engineer> findAllByFirstNameLikeOrLastNameLikeAndStatus(String firstName, String lastName, Status status);

    Optional<Engineer> findByLastName(String lastName);

    Optional<Engineer> findByLogin(String login);

    Collection<Engineer> findAllByLastNameLike(String lastName);

}
