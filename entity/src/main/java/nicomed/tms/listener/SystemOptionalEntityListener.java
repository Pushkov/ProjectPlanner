package nicomed.tms.listener;

import lombok.extern.slf4j.Slf4j;
import nicomed.tms.system.entity.SystemOptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;

@Slf4j
@Component
public class SystemOptionalEntityListener {

    private final AuditLogService auditLogService;

    public static final String AUDIT_ACTIVITY = "system_option";

    @Autowired
    public SystemOptionalEntityListener(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }


    @PreUpdate
    public void loggingPreUpdateSystemOption(SystemOptionEntity entity) {
        log.warn("PreUpdate {}", entity.getId());
        auditLogService.createLogs(AUDIT_ACTIVITY, "pre update " + entity.getId());

    }

    @PostUpdate
    public void loggingPostUpdateSystemOption(SystemOptionEntity entity) {
        log.warn("PostUpdate {}", entity.getId());
        auditLogService.createLogs(AUDIT_ACTIVITY, "post update " + entity);
    }
}
