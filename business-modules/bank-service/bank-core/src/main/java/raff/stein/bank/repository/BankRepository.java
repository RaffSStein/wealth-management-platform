package raff.stein.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raff.stein.bank.model.entity.BranchEntity;

import java.util.UUID;

@Repository
public interface BankRepository  extends JpaRepository<BranchEntity, UUID> {
}
