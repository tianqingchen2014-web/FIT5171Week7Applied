package prolist.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by yli on 10/03/15.
 * Updated by yqtian for version 2.0.
 */
public abstract class SeriablizableEntity {

    @DatabaseField(generatedId = true)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
