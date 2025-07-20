package raff.stein.proposalservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class ProposalEntity {

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

    // Getters and setters
}

