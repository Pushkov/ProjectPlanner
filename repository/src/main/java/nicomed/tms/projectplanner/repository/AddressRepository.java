package nicomed.tms.projectplanner.repository;

import nicomed.tms.projectplanner.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
