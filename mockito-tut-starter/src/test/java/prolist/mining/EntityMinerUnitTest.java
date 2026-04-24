package prolist.mining;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import prolist.dataaccess.AuditLogDAO;
import prolist.dataaccess.ProposalDAO;
import prolist.dataaccess.UserDAO;
import prolist.dataaccess.UserProposalDAO;
import prolist.model.Proposal;
import prolist.model.User;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by yli on 24/03/2016.
 * Updated by yqtian for version 2.0.
 */
@Tag("extra")
public class EntityMinerUnitTest {
    private UserDAO userDAO;
    private ProposalDAO proposalDAO;
    private UserProposalDAO userProposalDAO;
    private AuditLogDAO auditLogDAO;

    private EntityMiner miner;
    private User u1;
    private User u2;
    private User u3;
    private Proposal p1;
    private Proposal p2;

    @BeforeEach
    public void setUp() {
        userDAO = mock(UserDAO.class);
        proposalDAO = mock(ProposalDAO.class);
        userProposalDAO = mock(UserProposalDAO.class);
        auditLogDAO = mock(AuditLogDAO.class);

        miner = new EntityMiner(userDAO, proposalDAO, userProposalDAO, auditLogDAO);

        u1 = createUser("u1@example.com");
        u2 = createUser("u2@example.com");
        u3 = createUser("u3@example.com");

        p1 = new Proposal();
        p2 = new Proposal();

    }

    @Test
    public void kLargerThanUsersReturnsAllUsers() throws SQLException {
        List<User> list = Lists.newArrayList(u1, u2, u3);
        when(userDAO.getAll()).thenReturn(list);

        when(userProposalDAO.getProposalsForUser(u1)).thenReturn(Sets.newHashSet(p1, p2));
        when(userProposalDAO.getProposalsForUser(u2)).thenReturn(Sets.newHashSet(p1));
        when(userProposalDAO.getProposalsForUser(u3)).thenReturn(Sets.newHashSet());

        List<User> result = miner.retrieveWorkaholics(10);
        assertEquals(3, list.size());
        assertEquals(list, result);
    }

    @Test
    public void normalTop1ActiveUserOf3Users() throws SQLException {
        when(userDAO.getAll()).thenReturn(Lists.newArrayList(u1, u2, u3));

        when(userProposalDAO.getProposalsForUser(u1)).thenReturn(Sets.newHashSet(p1, p2));
        when(userProposalDAO.getProposalsForUser(u2)).thenReturn(Sets.newHashSet(p1));
        when(userProposalDAO.getProposalsForUser(u3)).thenReturn(Sets.newHashSet());

        List<User> list = miner.retrieveWorkaholics(1);
        assertEquals(1, list.size());

        assertEquals(u1, list.get(0));
    }

    @Test
    public void normalTop2ActiveUserOf3Users() throws SQLException {
        when(userDAO.getAll()).thenReturn(Lists.newArrayList(u1, u2, u3));

        when(userProposalDAO.getProposalsForUser(u1)).thenReturn(Sets.newHashSet(p1, p2));
        when(userProposalDAO.getProposalsForUser(u2)).thenReturn(Sets.newHashSet(p1));
        when(userProposalDAO.getProposalsForUser(u3)).thenReturn(Sets.newHashSet());

        List<User> list = miner.retrieveWorkaholics(2);
        assertEquals(2, list.size());

        assertEquals(Lists.newArrayList(u1, u2), list);
    }

    @Test
    public void top2ActiveUserWithSameNoProposalsOf3Users() throws SQLException {
        when(userDAO.getAll()).thenReturn(Lists.newArrayList(u1, u2, u3));

        when(userProposalDAO.getProposalsForUser(u1)).thenReturn(Sets.newHashSet(p1, p2));
        when(userProposalDAO.getProposalsForUser(u2)).thenReturn(Sets.newHashSet(p1, p2));
        when(userProposalDAO.getProposalsForUser(u3)).thenReturn(Sets.newHashSet(p2));

        List<User> list = miner.retrieveWorkaholics(2);
        assertEquals(2, list.size());

        assertEquals(Lists.newArrayList(u1, u2), list);
    }

    @Test
    public void emptySetForLoneSupervisors() throws Exception {
        when(userProposalDAO.getProposalsForUser(u1)).thenReturn(Sets.newHashSet(p1));
        when(userProposalDAO.getSupervisorsForProposal(p1)).thenReturn(Sets.newHashSet(u1));

        assertEquals(Sets.newHashSet(), miner.retrieveBuddies(u1, 1));
    }

    @Test
    public void correctBuddies() {
        when(userProposalDAO.getProposalsForUser(u1)).thenReturn(Sets.newHashSet(p1, p2));

        when(userProposalDAO.getSupervisorsForProposal(p1)).thenReturn(Sets.newHashSet(u1, u2));
        when(userProposalDAO.getSupervisorsForProposal(p2)).thenReturn(Sets.newHashSet(u1, u2, u3));

        assertEquals(Sets.newHashSet(u2), miner.retrieveBuddies(u1, 1));
    }

    @Test
    public void correctEqualBuddies() {
        when(userProposalDAO.getProposalsForUser(u1)).thenReturn(Sets.newHashSet(p1, p2));

        when(userProposalDAO.getSupervisorsForProposal(p1)).thenReturn(Sets.newHashSet(u1, u2, u3));
        when(userProposalDAO.getSupervisorsForProposal(p2)).thenReturn(Sets.newHashSet(u1, u2, u3));

        assertEquals(1, miner.retrieveBuddies(u1, 1).size());
        assertTrue(Sets.newHashSet(u2, u3).containsAll(miner.retrieveBuddies(u1, 1)));
    }

    @Test
    public void nonCollaboratingUsersAreLoneStars() throws SQLException {
        when(userDAO.getAll()).thenReturn(Lists.newArrayList(u1, u2, u3));

        when(userProposalDAO.getProposalsForUser(u1)).thenReturn(Sets.newHashSet(p2));
        when(userProposalDAO.getProposalsForUser(u2)).thenReturn(Sets.newHashSet(p1));
        when(userProposalDAO.getProposalsForUser(u3)).thenReturn(Sets.newHashSet(p1));
        when(userProposalDAO.getSupervisorsForProposal(p2)).thenReturn(Sets.newHashSet(u1));
        when(userProposalDAO.getSupervisorsForProposal(p1)).thenReturn(Sets.newHashSet(u2, u3));

        assertEquals(1, miner.retrieveLoneStars().size());
        assertTrue(miner.retrieveLoneStars().contains(u3));
    }

    private User createUser(String email) {
        User u1 = new User();
        u1.setEmail(email);
        return u1;
    }
}
