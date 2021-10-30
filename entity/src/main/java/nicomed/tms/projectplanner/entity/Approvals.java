package nicomed.tms.projectplanner.entity;

import javax.persistence.Embeddable;
import java.time.Month;
import java.time.OffsetDateTime;

@Embeddable
public class Approvals {
    private String designer;
    private OffsetDateTime designerSign;
    private String verifier;
    private OffsetDateTime verifierSign;
    private String normControl;
    private OffsetDateTime normControlSign;
    private String agree;
    private OffsetDateTime agreeSign;
    private String approve;
    private OffsetDateTime approveSign;
}
