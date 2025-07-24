package raff.stein.proposalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raff.stein.proposalservice.model.entity.ProposalEntity;

public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {
}

