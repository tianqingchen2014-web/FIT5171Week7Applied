package prolist.logics;

import prolist.model.AuditLog;
import prolist.model.Proposal;
import prolist.model.SeriablizableEntity;
import prolist.model.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yli on 25/02/2016.
 * Updated by yqtian for version 2.0.
 */
public class Validator {
    /**
     * Validates the new value <code>newValue</code> for attribute <code>field</code>.
     *
     * @param target the object that the new value is being set
     * @param field the attribute name
     * @param newValue the new value
     * @param <T>
     * @return true if validation is successful, otherwise throws a ValidationException.
     */
    public <T extends SeriablizableEntity> boolean validate(T target, String field, Object newValue) {
        Object newVal = newValue;
        Method setterMethod;
        if (target instanceof User) {
            User user = (User) target;
            field = field.toLowerCase();
            setterMethod = findSetterMethod(User.class, field);
            switch (field) {
                case "lastName":
                case "email":
                case "password":
                case "role":
                    checkForNull(field, newValue);
                    newVal = newValue;
                    break;
            }
            if ("role".equals(field)) {
                newVal = User.Role.valueOf(newValue.toString());
            }
            //TODO: complete this according to your definition

            setNewValue(user, newVal, setterMethod);
        } else if (target instanceof Proposal) {
            //TODO: complete this according to your definition
        } else if (target instanceof AuditLog) {
            //TODO: complete this according to your definition
        } else {
            throw new ValidationException("Invalid object type: " + target.getClass().getCanonicalName());
        }

        return true;
    }

    private <T extends SeriablizableEntity> Method findSetterMethod(Class<T> clazz, String fieldName) {
        Method setterMethod;
        try {
            Field declaredField = User.class.getDeclaredField(fieldName.toLowerCase());
            setterMethod = clazz.getMethod("set" + fieldName.substring(0, 1).toUpperCase() +
                    fieldName.substring(1), declaredField.getType());
        } catch (NoSuchMethodException e) {
            throw new ValidationException("No setter method found for attribute: " + fieldName, e);
        } catch (NoSuchFieldException e) {
            throw new ValidationException("No such attribute: " + fieldName, e);
        }
        return setterMethod;
    }

    private <T extends SeriablizableEntity> void setNewValue(T entity, Object newVal, Method setterMethod) {
        try {
            setterMethod.invoke(entity, newVal);
        } catch (Exception e) {
            throw new ValidationException("Attribute: " + newVal + " cannot be set for object: " + entity);
        }
    }

    private void checkForNull(String field, Object newValue) {
        if (null == newValue) {
            throw new ValidationException("Attribute: " + field + " cannot be null.");
        }
    }
}
