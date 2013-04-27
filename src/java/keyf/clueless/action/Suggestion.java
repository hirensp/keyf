package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.TurnManager;
import keyf.clueless.data.Player;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;

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
        this.suspect = suspect;
        this.weapon = weapon;
        this.room = room;
    }

    @Override
    public void performAction(Game game)
    {
        game.getBoard().setLocation(suspect, room);
        game.getBoard().setRoom(weapon, room);

        TurnManager turnManager = game.getTurnManager();

        Player currentPlayer = turnManager.getCurrentPlayer();

        // Set the next player active.
        turnManager.nextActivePlayer();

        // This Player will have to answer the suggestion.
        Player activePlayer = turnManager.getCurrentlyActivePlayer();

        for (Player player : game.getPlayers())
        {
            State.Builder stateBuilder
                    = new State.Builder(game.getState(player));

            if (currentPlayer.equals(player))
            {
                stateBuilder.removeAction(this);
            }
            else if (activePlayer.equals(player))
            {
                // TODO add the PossibleRefute action
//                stateBuilder.addAction()
            }

            stateBuilder.setSuspetMessage(
                    String.format(SUSPECT_MESSAGE, suspect, weapon, room));

            stateBuilder.setLogMessage(
                    String.format(LOG_MESSAGE, suspect, weapon, room));

            game.setState(player, stateBuilder.build());
        }
    }
}