package nicomed.tms.projectplanner.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

@Embeddable
public class ProjectApprovals {

    @ManyToOne
    private Engineer designer;
    private OffsetDateTime designerSign;
    @ManyToOne
    private Engineer verifier;
    private OffsetDateTime verifierSign;
    @ManyToOne
    private Engineer normControl;

    private OffsetDateTime normControlSign;
    @ManyToOne
    private Engineer agree;
    private OffsetDateTime agreeSign;
    @ManyToOne
    private Engineer approve;
    private OffsetDateTime approveSign;
}
