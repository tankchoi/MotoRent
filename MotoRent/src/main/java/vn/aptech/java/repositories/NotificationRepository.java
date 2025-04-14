package vn.aptech.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.java.models.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);
}
