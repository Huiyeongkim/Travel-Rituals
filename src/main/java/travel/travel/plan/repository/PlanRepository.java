package travel.travel.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import travel.travel.plan.domain.Plan;

public interface PlanRepository extends JpaRepository<Plan, String> {

}
