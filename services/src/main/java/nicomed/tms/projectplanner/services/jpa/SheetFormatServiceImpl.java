package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.format.FormatDto;
import nicomed.tms.projectplanner.entity.SheetFormat;
import nicomed.tms.projectplanner.enums.Format;
import nicomed.tms.projectplanner.mapper.FormatMapper;
import nicomed.tms.projectplanner.repository.SheetFormatRepository;
import nicomed.tms.projectplanner.services.SheetFormatService;
import nicomed.tms.projectplanner.services.config.JpaImpl;

import java.util.List;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.throwNotFoundByIdException;

@RequiredArgsConstructor
@JpaImpl
public class SheetFormatServiceImpl implements SheetFormatService {

    private final SheetFormatRepository sheetFormatRepository;
    private final FormatMapper mapper;

    @Override
    public List<FormatDto> findAll() {
        return sheetFormatRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FormatDto findById(Long id) {
        return sheetFormatRepository.findById(id).map(mapper::mapToDto)
                .orElseThrow(() -> throwNotFoundByIdException(SheetFormat.class, id));
    }

    @Override
    public FormatDto findByFormat(String formatName) {
        Format format = Format.getByName(formatName);
        return mapper.mapToDto(sheetFormatRepository.findByFormat(format));
    }

    @Override
    public SheetFormat findEntityByFormatName(String formatName) {
        Format format = Format.getByName(formatName);
        return sheetFormatRepository.findByFormat(format);
    }

}
