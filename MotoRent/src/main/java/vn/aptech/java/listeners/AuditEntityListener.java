package vn.aptech.java.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.aptech.java.models.AuditLog;
import vn.aptech.java.repositories.AuditLogRepository;
import vn.aptech.java.utils.SpringContext;

import java.util.Optional;

@Component
public class AuditEntityListener {
    // ObjectMapper cho việc chuyển đổi JSON
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @PrePersist
    public void prePersist(Object entity) {
        saveAuditLog(entity, "CREATE", null);
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        String oldData = getOldData(entity);
        saveAuditLog(entity, "UPDATE", oldData);
    }

    @PreRemove
    public void preRemove(Object entity) {
        saveAuditLog(entity, "DELETE", serializeToJson(entity));
    }

    private void saveAuditLog(Object entity, String actionType, String oldData) {
        String username = getCurrentUsername();

        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName(entity.getClass().getSimpleName());
        auditLog.setEntityId(getEntityId(entity));
        auditLog.setActionType(actionType);
        auditLog.setOldData(oldData);
        auditLog.setNewData(serializeToJson(entity));
        auditLog.setChangedBy(username);

        // Lưu thông qua SpringContext
        AuditLogRepository auditLogRepository = SpringContext.getBean(AuditLogRepository.class);
        auditLogRepository.save(auditLog);
    }

    private String getOldData(Object entity) {
        try {
            Long entityId = getEntityId(entity);
            if (entityId == null) {
                return "{}";
            }

            EntityManager em = SpringContext.getBean(EntityManager.class);
            Object originalEntity = em.find(entity.getClass(), entityId);

            if (originalEntity == null) {
                return "{}";
            }

            return serializeToJson(originalEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private String serializeToJson(Object entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    private Long getEntityId(Object entity) {
        try {
            return (Long) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            return null;
        }
    }

    private String getCurrentUsername() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(auth -> {
                    Object principal = auth.getPrincipal();
                    return (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : auth.getName();
                })
                .orElse("SYSTEM");
    }
}
