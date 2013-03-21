package core;

import java.awt.Point;

/**
 * This framework is written for the Winterschool 2013
 * Otto-von-Guericke University - FIN/ITI 
 *
 * This enumerator consists of every direction an agent can move or see.
 * 
 * @author Daniel Kottke, Georg Krempl
 */
public enum Direction {

	NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

	/**
	 * This method returns an array of every movable Direction.
	 * 
	 * @return Array of movable Directions
	 */
	public static Direction[] getMovables() {
		Direction[] res = new Direction[4];
		res[0] = NORTH;
		res[1] = EAST;
		res[2] = SOUTH;
		res[3] = WEST;
		return res;
	}

	/**
	 * This function converts the Direction into a direction vector, such that
	 * the following formula is fulfilled: 
	 * newPosition = oldPosition + direction.getVector()
	 * 
	 * @return direction vector of Direction
	 */
	public Point getVector() {
		switch (this) {
		case NORTH:
			return new Point(+0, -1);
		case NORTHEAST:
			return new Point(+1, -1);
		case EAST:
			return new Point(+1, +0);
		case SOUTHEAST:
			return new Point(+1, +1);
		case SOUTH:
			return new Point(+0, +1);
		case SOUTHWEST:
			return new Point(-1, +1);
		case WEST:
			return new Point(-1, +0);
		case NORTHWEST:
			return new Point(-1, -1);
		default:
			return new Point(+0, +0);
		}
	}
}
