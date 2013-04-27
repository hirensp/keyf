package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.TurnManager;
import keyf.clueless.data.Item;
import keyf.clueless.data.Player;
import static keyf.util.ParamUtil.*;

/**
 * Action that refutes another Player's suggestion.
 * 
 * @author jonathanpomper
 */
public class Refute implements Action
{    
    private final Item refuteItem;

    private final static  String REFUTAL_MESSAGE
            = "I can refute that suggestion! {0}";

    private final static String LOG_MESSAGE
            = "{0} refuted the suggestion.";

    /**
     * The item that refutes a suggestion.
     * 
     * @param item The item
     */
    public Refute(Item item)
    {
        this.refuteItem = requireNonNull(item);
    }
    
    /**
     * Response to a suggestion
     * 
     * @param game
     */
    @Override
    public void performAction(Game game)
    {
        TurnManager turnManager = game.getTurnManager();

        // This is the player that made the refutal
        Player currentlyActivePlayer = turnManager.getCurrentlyActivePlayer();

        // This is the player that needs to see the refutal
        Player currentPlayer = turnManager.getCurrentPlayer();
        
        // Create a new state for each player
        for (Player player : game.getPlayers())
        {
            State.Builder stateBuilder 
                    = new State.Builder(game.getState(player));

            if (player.equals(currentPlayer))
            {
                // For the player that made the suggestion, add the refuteItem
                // to the message
                stateBuilder.setSuspetMessage(
                        String.format(REFUTAL_MESSAGE, refuteItem))
                        .makeAllActionsAvailable();
            }
            else
            {
                // For all other players, ensure they have no actions, and
                // that they see the message (without the item)
                stateBuilder.clearActions();
                stateBuilder.setSuspetMessage(REFUTAL_MESSAGE);
            }

            // Add the log message to all player's states.
            stateBuilder.setLogMessage(String.format(
                    LOG_MESSAGE, currentlyActivePlayer.getIdentifier()));

            //Updates messages and log all players via game.setState().
            game.setState(player, stateBuilder.build());
        }

        // Return control to the player whose turn it currently is.
        turnManager.setCurrentPlayerActive();
    }
}