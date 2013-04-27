package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.TurnManager;
import keyf.clueless.data.Player;

/**
 * A Player should only see this action if they have no cards that can refute
 * the Suggestion.
 */
public class UnableToRefute implements Action
{
    private final static String SUSPECT_MESSAGE
            = "I cannot refute that suggestion.";

    private final static String LOG_MESSAGE
            = "{0} was unable to refute the suggestion.";

    @Override
    public void performAction(Game game)
    {
        TurnManager turnManager = game.getTurnManager();

        // This is the player who's unable to refute the suggestion.
        Player formerlyActive = turnManager.getCurrentlyActivePlayer();

        // This is the player who will next have to refute the suggestion.
        Player newlyActive = turnManager.nextActivePlayer();

        // This is the player that made the suggestion.
        Player currentPlayer = turnManager.getCurrentPlayer();

        for (Player player : game.getPlayers())
        {
            State.Builder stateBuilder
                    = new State.Builder(game.getState(player));

            if (formerlyActive.equals(player))
            {
                stateBuilder.removeAction(this);
            }

            if (newlyActive.equals(player))
            {
                if (newlyActive.equals(currentPlayer))
                {
                    // No one could refute the suggestion. Control returns to
                    // the current player
                    stateBuilder.makeAllActionsAvailable();
                }
                else
                {
                    // The next player needs to answer the suggestion.
                    // TODO - how can we tell if the next player has the
                    //        appropriate cards?
//                    if (player.getCards().contains(weapon/suspect/room))
//                    {
                    stateBuilder.addAction(new PossibleRefutal(player));
//                    }
//                    else
//                    {
                    // TODO - need new PossibleAction.
//                    state.Builder.addAction(new PossibleUnableToRefute);
//                      }
                }
            }

            stateBuilder.setSuspetMessage(SUSPECT_MESSAGE);

            stateBuilder.setLogMessage(
                    String.format(LOG_MESSAGE, formerlyActive.getIdentifier()));

            game.setState(player, stateBuilder.build());
        }
    }
}