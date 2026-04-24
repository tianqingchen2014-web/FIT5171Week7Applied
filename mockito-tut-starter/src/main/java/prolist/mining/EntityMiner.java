package prolist.mining;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import prolist.dataaccess.AuditLogDAO;
import prolist.dataaccess.ProposalDAO;
import prolist.dataaccess.UserDAO;
import prolist.dataaccess.UserProposalDAO;
import prolist.model.Proposal;
import prolist.model.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by yli on 23/03/2016.
 * Updated by yqtian for version 2.0.
 */
public class EntityMiner {
    private UserDAO userDAO;
    private ProposalDAO proposalDAO;
    private UserProposalDAO userProposalDAO;
    private AuditLogDAO auditLogDAO;

    public EntityMiner(UserDAO userDAO, ProposalDAO proposalDAO, UserProposalDAO userProposalDAO, AuditLogDAO auditLogDAO) {
        this.userDAO = userDAO;
        this.proposalDAO = proposalDAO;
        this.userProposalDAO = userProposalDAO;
        this.auditLogDAO = auditLogDAO;
    }

    /**
     * Retrieves the k most active users, based on the total number of proposals they are involved in.
     *
     * @param k a positive integer
     * @return a solted list of the top k most active users, with the most active being the head of the list.
     * @throws SQLException
     */
    public List<User> retrieveWorkaholics(int k) throws SQLException {
        //TODO: implement this
        List<User> users = userDAO.getAll();
        ListMultimap<Integer, User> userMap = ArrayListMultimap.create();

        for (User u : users) {
            Set<Proposal> proposals = userProposalDAO.getProposalsForUser(u);
            userMap.put(proposals.size(), u);
        }

        List<Integer> keys = Lists.newArrayList(userMap.keys());
        List<Integer> sortedKeys = Ordering.natural().greatestOf(keys, keys.size());
        List<User> results = Lists.newArrayList();

        for (Integer key : sortedKeys) {
            List<User> topUsers = userMap.get(key);
            if (results.size() + topUsers.size() <= k) {
                results.addAll(topUsers);
            } else {
                results.addAll(topUsers.subList(0, (k - results.size())));
            }
        }
        return results;
    }

    /**
     * Given a user, return the top-k other users that
     * he/she interacts with the most (through co-supervising proposals).
     * @param user
     * @param k
     * @return
     */
    public List<User> retrieveBuddies(User user, int k) {
        //TODO: implement this
        return null;
    }

    /**
     * Retrieve the top-k most dated proposals that are least modified.
     * @param k
     * @return
     */
    public List<Proposal> retrieveRelics(int k) {
        //TODO: implement this
        return null;
    }

    /**
     * NOTE: this functionality carries extra credit.
     *
     * Retrieve the k most common words in the background and
     * aims and outline fields across all proposals.
     * @param k
     * @return
     */
    public List<String> retrieveCommonThemes(int k) {
        //TODO: implement this
        return null;
    }

    /**
     * Retrieve the users who are the least collaborative: i.e., users who
     * have the least number of joint proposals with other users.
     * @return
     */
    public List<User> retrieveLoneStars() {
        //TODO: implement this
        return null;
    }
}
