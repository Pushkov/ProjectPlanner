package nicomed.tms.projectplanner.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

@Embeddable
public class DocumentApprovals {


    @ManyToOne
    private Engineer designer;
    private OffsetDateTime designerSign;
    @ManyToOne
    private Engineer verifier;
    private OffsetDateTime verifierSign;
    @ManyToOne
    private Engineer normControl;
    private OffsetDateTime normControlSign;
}
