package keyf.clueless.action;

import keyf.clueless.action.offer.OfferRefutal;
import keyf.clueless.action.offer.OfferUnableToRefute;

import java.text.MessageFormat;
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
 *
 * @author justin
 */
public class Suggestion implements Action
{
    private final String SUSPECT_MESSAGE = "I suggest the murder was committed "
            + "by {0} with the {1} in the {2}!";

    private final String LOG_MESSAGE
            = "{0} suggested {1} with the {2} in the {3}";

    private final Suspect suspect;
    private final Weapon weapon;

    public Suggestion(Suspect suspect, Weapon weapon)
    {
        this.suspect = requireNonNull(suspect);
        this.weapon = requireNonNull(weapon);
    }

    @Override
    public void performAction(Game game)
    {
        TurnManager turnManager = game.getTurnManager();

        Player currentPlayer = turnManager.getCurrentPlayer();

        // TODO don't cast like this: although we know that it has to be a Room
        //      if this method was called in the first place.
        Room room = (Room) game.getBoard().getLocation(currentPlayer.getSuspect());
        game.getBoard().setLocation(suspect, room);
        game.getBoard().setRoom(weapon, room);

        // Set the next player active; this Player will have to answer the
        // suggestion.
        Player activePlayer = turnManager.nextActivePlayer();

        for (Player player : game.getPlayers())
        {
            State.Builder stateBuilder
                    = new State.Builder(game.getNewestState(player));

            if (currentPlayer.equals(player))
            {
                stateBuilder.removeAction(this);
            }
            else if (activePlayer.equals(player))
            {
                Set<? extends Item> activePlayerCards = player.getCards();

                if (activePlayerCards.contains(suspect)
                        || activePlayerCards.contains(weapon)
                        || activePlayerCards.contains(room))
                {
                    // active player contains at least one card that can refute
                    // the suggestion.
                    stateBuilder.addAction(new OfferRefutal(player));
                }
                else
                {
                    // The player cannot refute the suggestion
                    stateBuilder.addAction(new OfferUnableToRefute());
                }
            }

            stateBuilder.setSuspectMessage(MessageFormat.format(
                    SUSPECT_MESSAGE, suspect, weapon, room));

            stateBuilder.setLogMessage(MessageFormat.format(
                    LOG_MESSAGE, suspect, weapon, room));

            game.addState(player, stateBuilder.build());
        }
    }
}