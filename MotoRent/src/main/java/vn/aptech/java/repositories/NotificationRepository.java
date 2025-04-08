package vn.aptech.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.java.models.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
