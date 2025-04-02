package vn.aptech.java.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;  // Tên bảng bị thay đổi
    private Long entityId;      // ID của bản ghi bị thay đổi
    private String actionType;  // CREATE, UPDATE, DELETE

    @Lob
    private String oldData;     // Dữ liệu cũ
    @Lob
    private String newData;     // Dữ liệu mới

    private String changedBy;   // Ai thực hiện thay đổi

    @CreationTimestamp
    private LocalDateTime timestamp; // Thời gian thay đổi

    public AuditLog() {}

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public String getNewData() {
        return newData;
    }

    public String getOldData() {
        return oldData;
    }

    public String getActionType() {
        return actionType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
