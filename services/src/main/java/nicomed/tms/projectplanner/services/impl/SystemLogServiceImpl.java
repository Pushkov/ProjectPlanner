package nicomed.tms.projectplanner.services.impl;

import lombok.RequiredArgsConstructor;
import nicomed.tms.listener.AuditLogService;
import nicomed.tms.projectplanner.dto.SystemLogDto;
import nicomed.tms.projectplanner.mapper.SystemLogMapper;
import nicomed.tms.system.entity.SystemLogEntity;
import nicomed.tms.system.repository.SystemLogRepository;
import nicomed.tms.system.services.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SystemLogServiceImpl implements SystemLogService, AuditLogService {

    @Lazy
    @Autowired
    private SystemLogRepository systemLogRepository;
    private final SystemLogMapper mapper;

    @Override
    public List<SystemLogDto> findAll() {
        return mapper.mapListToDto(systemLogRepository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void createLogs(String activity, String message) {
        systemLogRepository.save(SystemLogEntity.builder()
                .activity(activity)
                .message(message)
                .build());
    }
}
