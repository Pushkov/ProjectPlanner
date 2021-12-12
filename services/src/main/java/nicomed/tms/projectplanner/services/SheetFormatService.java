package nicomed.tms.projectplanner.services;

import nicomed.tms.projectplanner.dto.document.format.FormatDto;
import nicomed.tms.projectplanner.entity.SheetFormat;

import java.util.List;

public interface SheetFormatService {

    List<FormatDto> findAll();


    FormatDto findById(Long id);

    FormatDto findByFormat(String format);

    SheetFormat findEntityByFormatName(String name);

}
