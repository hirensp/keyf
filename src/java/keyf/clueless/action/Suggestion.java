package keyf.clueless.action;

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
    private final Room room;

    public Suggestion(Suspect suspect, Weapon weapon, Room room)
    {
        this.suspect = requireNonNull(suspect);
        this.weapon = requireNonNull(weapon);
        this.room = requireNonNull(room);
    }

    @Override
    public void performAction(Game game)
    {
        game.getBoard().setLocation(suspect, room);
        game.getBoard().setRoom(weapon, room);

        TurnManager turnManager = game.getTurnManager();

        Player currentPlayer = turnManager.getCurrentPlayer();

        // Set the next player active; this Player will have to answer the
        // suggestion.
        Player activePlayer = turnManager.nextActivePlayer();

        for (Player player : game.getPlayers())
        {
            State.Builder stateBuilder
                    = new State.Builder(game.getLatestState(player));

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

            stateBuilder.setSuspectMessage(
                    String.format(SUSPECT_MESSAGE, suspect, weapon, room));

            stateBuilder.setLogMessage(
                    String.format(LOG_MESSAGE, suspect, weapon, room));

            game.addState(player, stateBuilder.build());
        }
    }
}