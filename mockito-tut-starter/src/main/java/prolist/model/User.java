package prolist.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yli on 23/02/2016.
 * Updated by yqtian for version 2.0.
 */

@DatabaseTable(tableName = "users")
public class User extends SeriablizableEntity {
    public enum Role {
        ADMINISTRATOR, ACADEMIC;
    }

    @DatabaseField
    private String firstName;

    @DatabaseField(canBeNull = false)
    private String lastName;

    @DatabaseField(canBeNull = false, unique = true)
    private String email;

    @DatabaseField(canBeNull = false)
    private String password;

    @DatabaseField
    private String phone;

    @DatabaseField(canBeNull = false)
    private Role role;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Role role, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (null == role) {
            throw new IllegalArgumentException("Role cannot be null.");
        }
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setPassword(String password) {
        if (null == password) {
            throw new IllegalArgumentException("Password cannot be null.");
        } else if (password.trim().equals("")) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return 31 * email.hashCode();
    }
}
