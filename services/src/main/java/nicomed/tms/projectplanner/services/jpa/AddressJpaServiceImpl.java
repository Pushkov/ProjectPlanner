package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.Address;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.repository.AddressRepository;
import nicomed.tms.projectplanner.services.AddressService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
@JpaImpl
public class AddressJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Address, Long> implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public JpaRepository<Address, Long> getRepository() {
        return addressRepository;
    }
}
