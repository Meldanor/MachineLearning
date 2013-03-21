package core;

import java.awt.Point;
import java.util.ArrayList;

/**
 * This framework is written for the Winterschool 2013
 * Otto-von-Guericke University - FIN/ITI 
 * 
 * This class controls the Agent.
 * No further documentation.
 * 
 * @author Daniel Kottke, Georg Krempl
 */
public class AgentsProceed {

	private GridWorld gridWorld = null;
	private IAgent agent = null;
    private Point agentPosition = null;
    
	private boolean isSr = true;
	private boolean resetAgent = false;
	private int maxSteps = -1;
	private int counter = 0;
    
	private ArrayList<Integer> evalRunVector = new ArrayList<Integer>();
	private ArrayList<Boolean> evalMissionVector = new ArrayList<Boolean>();
	
	    
    public AgentsProceed(GridWorld gridWorld, IAgent agent, boolean isSr){
    	this.gridWorld = gridWorld;
    	this.agent = agent;
    	this.agentPosition = this.gridWorld.getStartPosition();
    	
    	this.isSr = isSr;
    }

    /**
     * Makes one move and returns true, if one run is over.
     * 
     * @return
     */
    private boolean makeOneMove(){
    	Senses senses = new Senses(this.gridWorld, this.agentPosition);
    	
    	// ask agent for Direction
    	Direction d = null;
    	if (this.isSr){
    		d = this.agent.getAction(senses);
    	}
    	else{
    		d = this.agent.getAction(this.agentPosition, senses);
    	}
    	
    	if (d==Direction.NORTHEAST || d==Direction.NORTHWEST || d==Direction.SOUTHEAST || d==Direction.SOUTHWEST){
        	this.agent.agentFailed();
        	System.out.println("Agent only can move NORTH, SOUTH, EAST, WEST!");
//        	System.out.println(this.evalRunVector.size()+". Run: "+this.evalRunVector.get(this.evalRunVector.size()-1)+" Steps to fail");
        	this.evalMissionVector.add(false);
        	this.evalRunVector.add(counter);
        	this.counter = 0;
        	return true;
    	}
    	
    	// move agent with Direction d
    	this.agentPosition.translate(d.getVector().x, d.getVector().y);
    	// increase counter
    	++counter;
    	
        //further response to agent 
        if (!this.gridWorld.isNotMine(this.agentPosition) || (maxSteps != -1 && counter >= maxSteps)){
        	this.agent.agentFailed();
//        	System.out.println(this.evalRunVector.size()+". Run: "+this.evalRunVector.get(this.evalRunVector.size()-1)+" Steps to fail");
        	this.evalMissionVector.add(false);
        	this.evalRunVector.add(counter);
        	this.counter = 0;
        	return true;
        }
        if (this.gridWorld.isGoal(this.agentPosition)){
        	this.agent.agentSucceeded();
//        	System.out.println(this.evalRunVector.size()+". Run: "+this.evalRunVector.get(this.evalRunVector.size()-1)+" Steps to succeed");
        	this.evalMissionVector.add(true);
        	this.evalRunVector.add(counter);
        	this.counter = 0;
        	return true;
        }
        return false;
    }

    public boolean doStep(){
		if(!this.resetAgent){
			// one move of the agent
	        this.resetAgent = makeOneMove();
			return false;
		}
		else{
			// reset Agent
        	this.agentPosition = gridWorld.getStartPosition();
        	this.resetAgent = false;
        	
    		return true;
		}
	}
    
    public boolean doTrial(){
		while(!this.resetAgent){
			// one move of the agent
	        this.resetAgent = makeOneMove();
		}
		// reset Agent
    	this.agentPosition = gridWorld.getStartPosition();
        this.resetAgent = false;
    	return true;
	}
    
    public GridWorld getGridWorld() {
		return gridWorld;
	}
    public Point getAgentPosition() {
		return agentPosition;
	}
    public String getAgentsName(){
    	return agent.getName();
    }
    public int[][] getEvalVector(){
    	int[][] res = new int[2][evalMissionVector.size()];
    	for (int i = 0; i < evalMissionVector.size(); ++i){
    		res[0][i] = this.evalRunVector.get(i);
    		res[1][i] = this.evalMissionVector.get(i).compareTo(true);
    	}
    	return res;
    }
    public String printLastTrialEval(){
    	int idx = this.evalMissionVector.size() -1;
    	if (this.evalMissionVector.get(idx))
    		return (idx+1)+". Trial: "+this.evalRunVector.get(idx)+" Steps to succeed";
    	else
    		return (idx+1)+". Trial: "+this.evalRunVector.get(idx)+" Steps to fail";
    }

	public void setMaxSteps(int i) {
		this.maxSteps = i;
	}
}
