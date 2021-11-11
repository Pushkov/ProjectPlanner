package nicomed.tms.projectplanner.repository.specification.filter;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class EngineerFilter {

    private String term;
}
