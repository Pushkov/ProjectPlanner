package nicomed.tms.projectplanner.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class DocumentApprovals {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DESIGNER_ID")
    private Engineer designer;
    @Column(name = "DESIGNER_SIGN")
    private LocalDateTime designerSign;
    @ManyToOne
    @JoinColumn(name = "VERIFIER_ID")
    private Engineer verifier;
    @Column(name = "VERIFIER_SIGN")
    private LocalDateTime verifierSign;
    @ManyToOne
    @JoinColumn(name = "NORM_CONTROL_ID")
    private Engineer normControl;
    @Column(name = "NORM_CONTROL_SIGN")
    private LocalDateTime normControlSign;
}
