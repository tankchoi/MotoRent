package vn.aptech.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import vn.aptech.java.models.Notification;
import vn.aptech.java.models.User;
import vn.aptech.java.repositories.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotificationToUser(User user, String message) {
        String destination = "/topic/user/" + user.getId();
        messagingTemplate.convertAndSend(destination, message);
        Notification notification = new Notification(user, message);
        notificationRepository.save(notification);
    }
}
