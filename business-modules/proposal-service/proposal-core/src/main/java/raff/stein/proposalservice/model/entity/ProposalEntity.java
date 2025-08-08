package raff.stein.proposalservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "proposals")
@Data
public class ProposalEntity extends BaseDateEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long advisorId;
    @ElementCollection
    private List<Long> productIds;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String riskProfile;
    private Double investmentAmount;
    private String notes;

}

