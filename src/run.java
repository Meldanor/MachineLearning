import java.awt.Point;

import core.AgentsProceed;
import core.GridAgentsWindow;
import core.GridWorld;
import core.IAgent;

/**
 * This framework is written for the Winterschool 2013
 * Otto-von-Guericke University - FIN/ITI 
 * 
 * Main class.
 * 
 * @author Daniel Kottke, Georg Krempl
 */

public class run {

	public static void main(String[] args) {
		// TODO: set to false for a kind of Reinforcement Learning
    	boolean isSRAgent = true;
    	
    	// creates an agent
		// TODO: replace by your own agent
    	IAgent agent = new RandomSRAgent();
    	
    	// load gridWorld
    	GridWorld gridWorld = null;
    	if (isSRAgent){
	    	gridWorld = new GridWorld("gridWorlds/SRWorld.txt");
	    	gridWorld.addStartPosition(new Point(1,3));
    	}else{
    		gridWorld = new GridWorld("gridWorlds/ReinfWorld.txt");
    	}
    	
    	// starting Application
    	AgentsProceed agentsProceed = new AgentsProceed(gridWorld, agent, isSRAgent);
    	agentsProceed.setMaxSteps(100); // Agent dies after specified steps
    	new GridAgentsWindow(agentsProceed);
	}

}
