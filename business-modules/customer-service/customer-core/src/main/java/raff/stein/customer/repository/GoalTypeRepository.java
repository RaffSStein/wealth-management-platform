package raff.stein.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raff.stein.customer.model.entity.goals.FinancialGoalTypeEntity;

@Repository
public interface GoalTypeRepository extends JpaRepository<FinancialGoalTypeEntity, Integer> {
}
