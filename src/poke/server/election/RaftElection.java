package poke.server.election;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poke.core.Mgmt.LeaderElection;
import poke.core.Mgmt.LeaderElection.ElectAction;
import poke.core.Mgmt.Management;
import poke.core.Mgmt.MgmtHeader;
import poke.core.Mgmt.VectorClock;

/**
 * Created by Jon Guan on 11/30/15.
 */
public class RaftElection implements Election{
    protected static Logger logger = LoggerFactory.getLogger("raftElection");

    private Integer nodeId;
    private ElectionState current;
    private int maxHops = -1; // unlimited
    private ElectionListener listener;

    public RaftElection(){

    }

    public RaftElection(Integer nodeId){ this.nodeId = nodeId; }

    /*
	 * (non-Javadoc)
	 *
	 * @see poke.server.election.Election#setListener(poke.server.election.
	 * ElectionListener)
	 */
    @Override
    public void setListener(ElectionListener listener) {
        this.listener = listener;
    }

    /*
     * (non-Javadoc)
     *
     * @see poke.server.election.Election#process(eye.Comm.LeaderElection)
     *
     * @return The Management instance returned represents the message to send
     * on. If null, no action is required. Not a great choice to convey to the
     * caller; can be improved upon easily.
     */
    @Override
    public Management process(Management mgmt) {
        if (!mgmt.hasElection())
            return null;

        LeaderElection req = mgmt.getElection();
        if (req.getExpires() <= System.currentTimeMillis()) {
            // election has expired without a conclusion?
        }

        Management rtn = null;
    }
}
