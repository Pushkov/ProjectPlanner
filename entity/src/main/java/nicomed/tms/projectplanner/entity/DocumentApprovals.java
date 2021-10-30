package nicomed.tms.projectplanner.entity;

import javax.persistence.Embeddable;
import java.time.OffsetDateTime;

@Embeddable
public class DocumentApprovals {
    private String designer;
    private OffsetDateTime designerSign;
    private String verifier;
    private OffsetDateTime verifierSign;
    private String normControl;
    private OffsetDateTime normControlSign;
}
