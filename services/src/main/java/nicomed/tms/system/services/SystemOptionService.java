package nicomed.tms.system.services;

import nicomed.tms.projectplanner.dto.system.SystemOptionDto;

import java.util.List;

public interface SystemOptionService {

    List<SystemOptionDto> findAll();

    void save(SystemOptionDto dto);

}
