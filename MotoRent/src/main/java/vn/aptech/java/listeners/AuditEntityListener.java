package vn.aptech.java.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import vn.aptech.java.models.AuditLog;
import vn.aptech.java.repositories.AuditLogRepository;
import vn.aptech.java.utils.SpringContext;

import java.util.Optional;

public class AuditEntityListener {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PrePersist
    public void prePersist(Object entity) {
        saveAuditLog(entity, "CREATE", null);
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        saveAuditLog(entity, "UPDATE", serializeToJson(entity));
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

        AuditLogRepository auditLogRepository = SpringContext.getBean(AuditLogRepository.class);
        auditLogRepository.save(auditLog);
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
