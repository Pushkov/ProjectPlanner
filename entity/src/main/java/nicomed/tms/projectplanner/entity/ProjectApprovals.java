package nicomed.tms.projectplanner.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class ProjectApprovals {

    @ManyToOne
    @JoinColumn(name = "DESIGNER_ID")
    private Engineer designer;
    @Column(name = "DESIGNER_SIGN")
    private OffsetDateTime designerSign;
    @ManyToOne
    @JoinColumn(name = "VERIFIER_ID")
    private Engineer verifier;
    @Column(name = "VERIFIER_SIGN")
    private OffsetDateTime verifierSign;
    @ManyToOne
    @JoinColumn(name = "NORM_CONTROL_ID")
    private Engineer normControl;
    @Column(name = "NORM_CONTROL_SIGN")
    private OffsetDateTime normControlSign;
    @ManyToOne
    @JoinColumn(name = "AGREE_ID")
    private Engineer agree;
    @Column(name = "AGREE_SIGN")
    private OffsetDateTime agreeSign;
    @ManyToOne
    @JoinColumn(name = "APPROVE_ID")
    private Engineer approve;
    @Column(name = "APPROVE_SIGN")
    private OffsetDateTime approveSign;
    @Column(name = "IS_APPROVE")
    private boolean isApprove;

    @PrePersist
    @PreUpdate
    private void hasApprove() {
        this.isApprove =
                !Objects.isNull(this.designer)
                        && !Objects.isNull(this.verifier)
                        && !Objects.isNull(this.normControl)
                        && !Objects.isNull(this.agree)
                        && !Objects.isNull(this.approve);
    }
}
