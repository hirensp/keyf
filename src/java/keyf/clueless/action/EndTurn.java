package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.TurnManager;
import keyf.clueless.data.Player;

/**
 * Ends the {@link TurnManager#getCurrentPlayer() current player's} turn.
 *
 * @author justin
 */
public class EndTurn implements Action
{
    private final static String SUSPECT_MESSAGE = "I have ended my turn.";
    private final static String LOG_MESSAGE = "{0} has ended his/her turn.";

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction(Game game)
    {
        // TODO-set the next player's actions in the State here? Probably...

        TurnManager turnManager = game.getTurnManager();

        Player currentPlayer = turnManager.getCurrentPlayer();

        for (Player player : game.getPlayers())
        {
            State.Builder builder = new State.Builder(game.getState(player));

            if (player.equals(currentPlayer))
            {
                builder.clearActions();
            }

            builder.setSuspetMessage(SUSPECT_MESSAGE);
            builder.setLogMessage(LOG_MESSAGE);

            game.setState(player, builder.build());
        }

        turnManager.nextPlayer();
    }
}