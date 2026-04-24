package prolist.dataaccess;

import prolist.model.AuditLog;

import java.sql.SQLException;

/**
 * Created by yli on 23/02/2016.
 * Updated by yqtian for version 2.0.
 */
public interface AuditLogDAO extends DAO<AuditLog> {
    AuditLog getAuditLogByProposalID(Long id) throws SQLException;
}
