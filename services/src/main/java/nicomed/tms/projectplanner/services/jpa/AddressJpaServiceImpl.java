package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.AddressDto;
import nicomed.tms.projectplanner.entity.Address;
import nicomed.tms.projectplanner.mapper.AddressMapper;
import nicomed.tms.projectplanner.repository.AddressRepository;
import nicomed.tms.projectplanner.services.AddressService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
@JpaImpl
public class AddressJpaServiceImpl extends AbstractJpaService<AddressDto, Address, Long> implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper mapper;

    @Override
    public JpaRepository<Address, Long> getRepository() {
        return addressRepository;
    }

    @Override
    public AddressDto mapToDto(Address entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Address mapToEntity(AddressDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public String getEntityClassName() {
        return Address.class.getSimpleName();
    }
}
