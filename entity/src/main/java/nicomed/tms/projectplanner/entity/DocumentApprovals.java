package nicomed.tms.projectplanner.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

@Embeddable
public class DocumentApprovals {


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
}
