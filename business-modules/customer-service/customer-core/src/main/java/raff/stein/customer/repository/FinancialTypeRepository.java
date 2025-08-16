package raff.stein.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raff.stein.customer.model.entity.financial.FinancialTypeEntity;

@Repository
public interface FinancialTypeRepository extends JpaRepository<FinancialTypeEntity, Integer> {
}
