import java.awt.Point;
import java.util.ArrayList;

import core.IAgent;
import core.Direction;
import core.Senses;

/**
 * This framework is written for the Winterschool 2013
 * Otto-von-Guericke University - FIN/ITI 
 * 
 * @author Daniel Kottke, Georg Krempl
 */

public class RandomSRAgent implements IAgent {
	@Override
	public String getName(){
		// returns agents name
		return "Random SR Agent";
	}
	
	@Override
	public Direction getAction(Senses senses) {
		// extraction of possible moves considering the agent's senses
		ArrayList<Direction> possibleMoves = new ArrayList<Direction>();
		for (Direction d : Direction.getMovables()){
			if (!senses.isMine(d)){
				possibleMoves.add(d);
			}
		}
		
		// get a random direction from the possible directions
		int dInt = (int) (Math.random()*possibleMoves.size());
		
		// return the random generated valid direction to move
		return possibleMoves.get(dInt);
	}

	@Override
	public Direction getAction(Point pos, Senses senses) {
		// this agent is not interested in its position, it just does, what a SR Agent would do
		return getAction(senses);
	}

	@Override
	public void agentFailed() {
		// when agent failed, it just tells it
		System.out.println("I failed");
	}

	@Override
	public void agentSucceeded() {
		// when agent succeeded, it just tells it
		System.out.println("I succeeded");
	}

}
