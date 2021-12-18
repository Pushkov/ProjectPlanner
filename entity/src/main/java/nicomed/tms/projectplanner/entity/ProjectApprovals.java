package nicomed.tms.projectplanner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProjectApprovals {

    @ManyToOne
    @JoinColumn(name = "DESIGNER_ID")
    private Engineer designer;
    @Column(name = "DESIGNER_SIGN")
    private LocalDate designerSign;
    @ManyToOne
    @JoinColumn(name = "VERIFIER_ID")
    private Engineer verifier;
    @Column(name = "VERIFIER_SIGN")
    private LocalDate verifierSign;
    @ManyToOne
    @JoinColumn(name = "NORM_CONTROL_ID")
    private Engineer normControl;
    @Column(name = "NORM_CONTROL_SIGN")
    private LocalDate normControlSign;
    @ManyToOne
    @JoinColumn(name = "AGREE_ID")
    private Engineer agree;
    @Column(name = "AGREE_SIGN")
    private LocalDate agreeSign;
    @ManyToOne
    @JoinColumn(name = "APPROVE_ID")
    private Engineer approve;
    @Column(name = "APPROVE_SIGN")
    private LocalDate approveSign;
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
