package nicomed.tms.projectplanner.services.impl;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.system.SystemOptionDto;
import nicomed.tms.projectplanner.mapper.SystemOptionMapper;
import nicomed.tms.system.entity.SystemOptionEntity;
import nicomed.tms.system.repository.SystemOptionRepository;
import nicomed.tms.system.services.SystemOptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SystemOptionServiceImpl implements SystemOptionService {

    private final SystemOptionRepository systemOptionRepository;
    private final SystemOptionMapper mapper;

    @Override
    public List<SystemOptionDto> findAll() {
        return systemOptionRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void save(SystemOptionDto dto) {
        SystemOptionEntity entity = systemOptionRepository.findById(dto.getId())
                .orElse(SystemOptionEntity.builder()
                        .id(dto.getId())
                        .build());
        entity.setValue(dto.getValue());
        systemOptionRepository.save(entity);
    }
}
