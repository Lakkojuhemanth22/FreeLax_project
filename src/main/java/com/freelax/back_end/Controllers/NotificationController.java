package com.freelax.back_end.Controllers;

import com.freelax.back_end.Entity.Notification;
import com.freelax.back_end.Services.NotificationService;
import com.freelax.back_end.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/freelancer/{freelancerId}")
    public List<Notification> getNotificationsForFreelancer(@PathVariable Long freelancerId) {
        return notificationService.getNotificationsForFreelancer(freelancerId);
    }

    @GetMapping("/company/{companyId}")
    public List<Notification> getNotificationsForCompany(@PathVariable Long companyId) {
        return notificationService.getNotificationsForCompany(companyId);
    }

    @PostMapping("/freelancer/{freelancerId}")
    public Notification createNotificationForFreelancer(@RequestBody Notification notification, @PathVariable Long freelancerId) {
        return notificationService.createNotificationForFreelancer(notification, freelancerId);
    }

    @PostMapping("/company/{companyId}")
    public Notification createNotificationForCompany(@RequestBody Notification notification, @PathVariable Long companyId) {
        return notificationService.createNotificationForCompany(notification, companyId);
    }

    @PutMapping("/{notificationId}/read")
    public Notification markAsRead(@PathVariable Long notificationId) {
        return notificationService.markAsRead(notificationId).orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
    }

    @PostMapping("/freelancer/{freelancerId}/job-alert")
    public Notification createJobAlert(@RequestBody Notification notification, @PathVariable Long freelancerId) {
        return notificationService.createJobAlertNotification(freelancerId, notification.getMessage(), notification.getCriteria());
    }

    @GetMapping("/freelancer/{freelancerId}/job-alerts")
    public List<Notification> getJobAlerts(@PathVariable Long freelancerId) {
        return notificationService.getJobAlerts(freelancerId);
    }
}
