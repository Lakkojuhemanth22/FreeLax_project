package com.freelax.back_end.Repository;


import com.freelax.back_end.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByFreelancerUserId(Long freelancerId);

    List<Notification> findByCompanyCompanyId(Long companyId);

    List<Notification> findByFreelancerUserIdAndAlertType(Long freelancerId, String alertType);
}
