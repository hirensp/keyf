package keyf.clueless.action;

import static keyf.util.ParamUtil.requireNonNull;

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
    // TODO - These log strings are not formatted correctly.

    private final static String MOVE_MESSAGE = "I have moved into the {0}.";

    private final static String LOG_MESSAGE = "{0} has moved into {1}";

    private final Location location;

    /**
     * Moves the current player to the given {@code location}.
     *
     * @param location the location where the current player is to be moved
     */
    public Move(Location location)
    {
        this.location = requireNonNull(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction(Game game)
    {
        Player currentPlayer = game.getTurnManager().getCurrentPlayer();

        game.getBoard().setLocation(currentPlayer.getSuspect(), location);
        
        for (Player player : game.getPlayers())
        {
            State.Builder stateBuilder
                    = new State.Builder(game.getState(player));

            if (player.equals(currentPlayer))
            {
                // remove "Move" from the possible actions in State
                stateBuilder.removeAction(this);
            }
            else
            {
                stateBuilder.setSuspetMessage(String.format(
                        MOVE_MESSAGE, location));
            }

            // add log message for all players.
            stateBuilder.setLogMessage(String.format(
                    LOG_MESSAGE, currentPlayer.getIdentifier(), location));

            game.setState(player, stateBuilder.build());
        }
    }
}