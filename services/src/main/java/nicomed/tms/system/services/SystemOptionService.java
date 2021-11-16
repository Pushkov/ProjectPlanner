package nicomed.tms.system.services;

import nicomed.tms.projectplanner.dto.SystemOptionDto;

import java.util.List;

public interface SystemOptionService {

    List<SystemOptionDto> findAll();

    void save(SystemOptionDto dto);

}
