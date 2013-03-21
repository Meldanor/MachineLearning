package core;

import java.awt.Point;

/**
 * This framework is written for the Winterschool 2013
 * Otto-von-Guericke University - FIN/ITI 
 * 
 * This class contains all the possible senses of an agent. Here an agent is
 * able to watch into eight Directions. For each Direction it is stored, if
 * there is a wall or not.
 * 
 * @author Daniel Kottke, Georg Krempl
 */

public class Senses {
	private boolean[] mines = new boolean[8];

	/**
	 * This Constructor generates the agents senses from a given GridWorld and
	 * the position of the agent.
	 * 
	 * @param gridWorld
	 * @param position
	 */
	public Senses(GridWorld gridWorld, Point position) {
		// for every direction, check if there is a wall
		for (Direction direction : Direction.values()) {
			Point newPosition = (Point) position.clone();
			newPosition.translate(direction.getVector().x,
					direction.getVector().y);

			mines[direction.ordinal()] = !gridWorld.isNotMine(newPosition);
		}
	}

	/**
	 * This method checks, if there is a wall in a given Direction.
	 * 
	 * @param d
	 *            Direction to look
	 * @return Boolean Returns true if there is a wall in Direction d.
	 */
	public boolean isMine(Direction d) {
		return mines[d.ordinal()];
	}

	/**
	 * This function returns the all Senses in a vector coded as the enum
	 * Directions. That means senses[d.ordinal()] = isWall(d).
	 * 
	 * @return Vector of booleans List of Walls
	 */
	public boolean[] getMineList() {
		return mines;
	}
}
