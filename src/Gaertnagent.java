import java.awt.Point;

import core.Direction;
import core.IAgent;
import core.Senses;

public class Gaertnagent implements IAgent {

    @Override
    public String getName() {
        return "Tabi";
    }

    @Override
    public Direction getAction(Senses senses) {

        if ((senses.isMine(Direction.EAST) || senses
                .isMine(Direction.NORTHEAST))
                && !senses.isMine(Direction.NORTH))
            return Direction.NORTH;
        else if ((senses.isMine(Direction.SOUTHEAST) || senses
                .isMine(Direction.SOUTH)) && !senses.isMine(Direction.EAST))
            return Direction.EAST;
        else if ((senses.isMine(Direction.SOUTHWEST) || senses
                .isMine(Direction.WEST)) && !senses.isMine(Direction.SOUTH))
            return Direction.SOUTH;
        else if ((senses.isMine(Direction.NORTHWEST) || senses
                .isMine(Direction.NORTH)) && !senses.isMine(Direction.WEST))
            return Direction.WEST;
        else
            return Direction.NORTH;

        // check north
        // boolean[] mineList = senses.getMineList();

        // if (senses.isMine(Direction.NORTHWEST) &&
        // senses.isMine(Direction.NORTH) && senses.isMine(Direction.NORTHEAST))
        // return Direction.WEST;
        // else if (senses.isMine(Direction.NORTHWEST) &&
        // senses.isMine(Direction.EAST) && senses.isMine(Direction.SOUTHEAST))
    }

    @Override
    public Direction getAction(Point pos, Senses senses) {
        return getAction(senses);
    }

    @Override
    public void agentFailed() {
        System.out.println("Fail!");
    }

    @Override
    public void agentSucceeded() {
        System.out.println("Win!");

    }

}
