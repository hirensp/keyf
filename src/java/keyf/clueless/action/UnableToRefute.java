package keyf.clueless.action;

import keyf.clueless.action.offer.OfferRefutal;
import keyf.clueless.action.offer.OfferUnableToRefute;
import java.util.Set;
import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.TurnManager;
import keyf.clueless.data.Item;
import keyf.clueless.data.Player;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;
import static keyf.util.ParamUtil.requireNonNull;

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

    private final Suspect suspect;

    private final Weapon weapon;

    private final Room room;

    /**
     * Creates a new instnace.
     *
     * @param suspect the suspect that could not be refuted
     * @param weapon the weapon that could not be refuted
     * @param room the room that could not be refuted
     */
    public UnableToRefute(Suspect suspect, Weapon weapon, Room room)
    {
        this.suspect = requireNonNull(suspect);
        this.weapon = requireNonNull(weapon);
        this.room = requireNonNull(room);
    }

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
                    = new State.Builder(game.getLatestState(player));

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
                    Set<? extends Item> playerCards = player.getCards();

                    // The next player needs to answer the suggestion.
                    if (playerCards.contains(suspect)
                            || playerCards.contains(weapon)
                            || playerCards.contains(room))
                    {
                        stateBuilder.addAction(new OfferRefutal(player));
                    }
                    else
                    {
                        stateBuilder.addAction(new OfferUnableToRefute());
                    }
                }
            }

            stateBuilder.setSuspectMessage(SUSPECT_MESSAGE);

            stateBuilder.setLogMessage(
                    String.format(LOG_MESSAGE, formerlyActive.getIdentifier()));

            game.addState(player, stateBuilder.build());
        }
    }
}