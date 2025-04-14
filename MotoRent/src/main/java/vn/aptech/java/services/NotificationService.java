package vn.aptech.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.aptech.java.models.Notification;
import vn.aptech.java.models.User;
import vn.aptech.java.repositories.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotificationToUser(User user, String message) {
        String destination = "/topic/user/" + user.getId();
        messagingTemplate.convertAndSend(destination, message);
        Notification notification = new Notification(user, message);
        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.findByEmail(currentUsername);
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
    }
}
