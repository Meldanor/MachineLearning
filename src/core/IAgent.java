package core;

import java.awt.Point;

/**
 * This framework is written for the Winterschool 2013
 * Otto-von-Guericke University - FIN/ITI 
 * 
 * An Agent is the main object of this framework. The task is to implement a good one.
 * 
 * @author Daniel Kottke, Georg Krempl
 */

public interface IAgent {
	/**
	 * This method just returns the name of the Agent.
	 * 
	 * @return Name of the Agent
	 */
	public String getName();

	/**
	 * This method chooses a direction for a Stimulus Response Agent. Stimulus
	 * Response Agents decide their action on given Senses.
	 * 
	 * @param senses
	 *            Senses of the agent
	 * @return Direction Returns a movable Direction (UP, DOWN, LEFT, RIGHT)
	 */
	public Direction getAction(Senses senses);

	/**
	 * This method chooses a direction for a more general Agent, that for
	 * example is possible to do Reinforcement Learning. This Agents decide its
	 * action on its actual position and given Senses.
	 * 
	 * @param pos
	 *            The actual position of the agent.
	 * @param senses
	 *            Senses of the agent
	 * @return Direction Returns a movable Direction (UP, DOWN, LEFT, RIGHT)
	 */
	public Direction getAction(Point pos, Senses senses);

	/**
	 * This function outputs the Agent that it failed its mission (e.g. reaching the goal).
	 */
	public void agentFailed();

	/**
	 * This function outputs the Agent that it succeeded its mission (e.g. reaching the goal).
	 */
	public void agentSucceeded();
}
