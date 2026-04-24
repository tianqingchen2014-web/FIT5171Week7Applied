package prolist.dataaccess;

import prolist.model.Proposal;
import prolist.model.User;
import prolist.model.UserProposal;

import java.util.Set;

/**
 * Created by yli on 23/03/2016.
 * Updated by yqtian for version 2.0.
 */
public interface UserProposalDAO extends DAO<UserProposal> {
    Set<Proposal> getProposalsForUser(User user);

    Set<User> getSupervisorsForProposal(Proposal proposal);
}
