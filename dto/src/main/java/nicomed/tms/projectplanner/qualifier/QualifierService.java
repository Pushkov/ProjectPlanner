package nicomed.tms.projectplanner.qualifier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QualifierService<T, ID> {

    JpaRepository<T, ID> getRepository();
}
