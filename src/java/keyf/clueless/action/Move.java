package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.data.Player;
import keyf.clueless.data.location.Location;

/**
 *
 * @author justin
 */
public class Move implements Action
{
    private final Location location;

    /**
     * Moves the current player to the given {@code location}.
     *
     * @param location the location where the current player is to be moved
     */
    public Move(Location location)
    {
        this.location = location;
    }

    @Override
    public void performAction(Game game)
    {
        Player currentPlayer = game.getTurnManager().getCurrentPlayer();

        game.getBoard().setLocation(currentPlayer.getSuspect(), location);
        
        for (Player player : game.getPlayers())
        {
            State state = game.getState(player);

            if (player.equals(currentPlayer))
            {
                // remove "Move" from the possible actions in State
            }
            else
            {
                // add message about the move (the player who made the move does
                // not need to be told he made the move)
            }

            // add log message for all players.
            // TODO there needs to be a State builder...
            game.setState(player, state);
        }
    }
}
