package com.freelax.back_end.Services;

import com.freelax.back_end.Entity.Freelancer;
import com.freelax.back_end.Entity.Notification;
import com.freelax.back_end.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsForFreelancer(Long freelancerId) {
        return notificationRepository.findByFreelancerUserId(freelancerId);
    }

    public List<Notification> getNotificationsForCompany(Long companyId) {
        return notificationRepository.findByCompanyCompanyId(companyId);
    }

    public Notification createNotificationForFreelancer(Notification notification, Long freelancerId) {
        // Add business logic if needed
        return notificationRepository.save(notification);
    }

    public Notification createNotificationForCompany(Notification notification, Long companyId) {
        // Add business logic if needed
        return notificationRepository.save(notification);
    }

    public Optional<Notification> markAsRead(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notification.ifPresent(n -> {
            n.setIsRead(true);
            notificationRepository.save(n);
        });
        return notification;
    }

    public Notification createJobAlertNotification(Long freelancerId, String message, String criteria) {
        Notification notification = new Notification();

        // Create a new Freelancer instance and set the userId
        Freelancer freelancer = new Freelancer();
        freelancer.setUserId(freelancerId);

        notification.setFreelancer(freelancer);
        notification.setMessage(message);
        notification.setAlertType("job_alert");
        notification.setCriteria(criteria);

        return notificationRepository.save(notification);
    }

    public List<Notification> getJobAlerts(Long freelancerId) {
        return notificationRepository.findByFreelancerUserIdAndAlertType(freelancerId, "job_alert");
    }
}
