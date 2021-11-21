package nicomed.tms.system.services;

import nicomed.tms.projectplanner.dto.system.SystemLogDto;

import java.util.List;

public interface SystemLogService {

    List<SystemLogDto> findAll();

    void createLogs(String activity, String message);
}
