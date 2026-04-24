package prolist.dataaccess;

import prolist.model.User;

import java.sql.SQLException;

/**
 * Created by yli on 10/03/15.
 * Updated by yqtian for version 2.0.
 */
public interface UserDAO extends DAO<User> {
    User getUserByEmail(String email) throws SQLException;
}
