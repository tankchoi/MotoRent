package vn.aptech.java.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.java.models.Notification;
import vn.aptech.java.services.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationApiController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getNotificationsForUser();
    }
}
