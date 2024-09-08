package com.freelax.back_end.Repository;

import com.freelax.back_end.Entity.FreelancerBadge;
import com.freelax.back_end.Entity.FreelancerBadgeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerBadgeRepository extends JpaRepository<FreelancerBadge, FreelancerBadgeId> {
}
