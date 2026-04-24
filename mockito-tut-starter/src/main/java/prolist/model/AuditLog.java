package prolist.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDateTime;

/**
 * Created by yli on 23/02/2016.
 * Updated by yqtian for version 2.0.
 */
@DatabaseTable(tableName = "audit_trail")
public class AuditLog extends SeriablizableEntity {
    public enum Changes {
        CREATE, MODIFY, DELETE
    }

    @DatabaseField
    private Long proposalId;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private LocalDateTime modifiedTime;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private User user;

    @DatabaseField(canBeNull = false)
    private Changes typeOfChange;

    @DatabaseField(canBeNull = false)
    private String fields;

    public AuditLog() {
    }

    public AuditLog(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Changes getTypeOfChange() {
        return typeOfChange;
    }

    public void setTypeOfChange(Changes typeOfChange) {
        this.typeOfChange = typeOfChange;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
