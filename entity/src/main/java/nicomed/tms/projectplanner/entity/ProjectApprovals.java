package nicomed.tms.projectplanner.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

@Embeddable
public class ProjectApprovals {

    @ManyToOne
    private Engineer designer;
    @Column(name = "designer_sign")
    private OffsetDateTime designerSign;
    @ManyToOne
    private Engineer verifier;
    @Column(name = "verifier_sign")
    private OffsetDateTime verifierSign;
    @ManyToOne
    private Engineer normControl;
    @Column(name = "norm_control_sign")
    private OffsetDateTime normControlSign;
    @ManyToOne
    private Engineer agree;
    @Column(name = "agree_sign")
    private OffsetDateTime agreeSign;
    @ManyToOne
    private Engineer approve;
    @Column(name = "approve_sign")
    private OffsetDateTime approveSign;
}
