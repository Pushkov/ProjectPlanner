package nicomed.tms.projectplanner.services.impl;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.SystemLogDto;
import nicomed.tms.projectplanner.mapper.SystemLogMapper;
import nicomed.tms.system.repository.SystemLogRepository;
import nicomed.tms.system.services.SystemLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SystemLogServiceImpl implements SystemLogService {

    private final SystemLogRepository systemLogRepository;
    private final SystemLogMapper mapper;

    @Override
    public List<SystemLogDto> findAll() {
        return mapper.mapListToDto(systemLogRepository.findAll());
    }
}
