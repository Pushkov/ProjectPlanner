package nicomed.tms.listener;

import org.springframework.stereotype.Service;

@Service
public interface AuditLogService {

    void createLogs(String activity, String message);
}
