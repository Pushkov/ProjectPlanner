package nicomed.tms.system.services;

import nicomed.tms.projectplanner.dto.SystemLogDto;

import java.util.List;

public interface SystemLogService {

    List<SystemLogDto> findAll();

}
