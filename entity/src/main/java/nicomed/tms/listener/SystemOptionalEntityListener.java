package nicomed.tms.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Slf4j
@Component
public class SystemOptionalEntityListener extends BaseLogEntityListener {

    public static final String AUDIT_ACTIVITY = "system_option";

    public SystemOptionalEntityListener(AuditLogService auditLogService) {
        super(auditLogService);
    }

    @Override
    protected String getLogActivity() {
        return AUDIT_ACTIVITY;
    }

    @PostPersist
    @Override
    protected void loggingPostPersist(Object entity) {
        super.loggingPostPersist(entity);
    }

    @PostUpdate
    @Override
    protected void loggingPostUpdate(Object entity) {
        super.loggingPostUpdate(entity);
    }

    @PostRemove
    @Override
    protected void loggingPostDelete(Object entity) {
        super.loggingPostDelete(entity);
    }
}
