package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.enums.Status;
import nicomed.tms.projectplanner.mapper.EngineerMapper;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.aspect.LoggegMethod;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.enums.Status.valueOf;
import static nicomed.tms.projectplanner.repository.specification.EngineerSpecification.findByTerm;
import static nicomed.tms.projectplanner.repository.specification.EngineerSpecification.lastNameStartWith;
import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.throwNotFoundByNameException;
import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.trowIncorrectPageAssignmentException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class EngineerJpaServiceImpl
        extends AbstractJpaService<EngineerDto, Engineer, Long>
        implements EngineerService, SearcheableService<Engineer> {

    private final EngineerRepository engineerRepository;
    private final EngineerMapper mapper;

    @Override
    public JpaRepository<Engineer, Long> getRepository() {
        return engineerRepository;
    }

    @Override
    public SearchableRepository<Engineer, ?> getSearchRepository() {
        return engineerRepository;
    }

    @Override
    public EngineerDto findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Long count() {
        return engineerRepository.count();
    }

    @LoggegMethod(value = "Engineer login={dto.login} created", activity = "engineer status")
    @Transactional
    @Override
    public void save(EngineerDto dto) {
        Engineer engineer = mapToEntity(dto);
        engineerRepository.save(engineer);
    }

    @LoggegMethod(value = "Engineer id={id} updated", activity = "engineer status")
    @Transactional
    @Override
    public void save(Long id, EngineerDto dto) {
        Engineer engineer = findEntityById(id);
        mapper.mapToEntity(engineer, dto);
    }

    @LoggegMethod(value = "Engineer id={id} deleted", activity = "engineer status")
    @Transactional
    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public List<EngineerDto> search(EngineerFilter engineerFilter) {
        Specification<Engineer> specification = findByTerm(engineerFilter.getTerm());
        return engineerRepository.findAll(specification).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @LoggegMethod(value = "Engineer id={id} set status '{statusName}'", activity = "engineer status")
    @Transactional
    @Override
    public void setStatus(Long id, String statusName) {
        Status status = valueOf(statusName);
        findEntityById(id).setStatus(status);
    }

    @Override
    public Page findPage(Integer page, Integer offset) {
        if (page >= 0 && offset > 0) {
            PageRequest pageRequest = PageRequest.of(page, offset);
            List<EngineerDto> engineerDtos = engineerRepository.findAll(pageRequest).stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
            return new PageImpl<>(engineerDtos);
        }
        throw trowIncorrectPageAssignmentException("Incorrect page assignment");
    }

    @Override
    public EngineerDto findByLastName(String lastName) {
        return engineerRepository.findByLastName(lastName).map(this::mapToDto)
                .orElseThrow(() -> throwNotFoundByNameException(getEntityClass(), lastName));
    }

    @Override
    public Collection<EngineerDto> findAllByLastNameStartWith(EngineerFilter engineerFilter) {
        Specification<Engineer> specification = lastNameStartWith(engineerFilter.getTerm());
        return engineerRepository.findAll(specification).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EngineerDto mapToDto(Engineer entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Engineer mapToEntity(EngineerDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<Engineer> getEntityClass() {
        return Engineer.class;
    }
}
