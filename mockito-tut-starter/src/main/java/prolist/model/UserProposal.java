package prolist.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by yli on 23/03/2016.
 * Updated by yqtian for version 2.0.
 */
public class UserProposal extends SeriablizableEntity {
    public final static String USER_ID_FIELD_NAME = "user_id";
    public final static String PROPOSAL_ID_FIELD_NAME = "proposal_id";

    @DatabaseField(foreign = true, columnName = USER_ID_FIELD_NAME)
    private User user;

    @DatabaseField(foreign = true, columnName = PROPOSAL_ID_FIELD_NAME)
    private Proposal proposal;

    public UserProposal() {
    }

    public UserProposal(User user, Proposal proposal) {
        this.user = user;
        this.proposal = proposal;
    }
}
