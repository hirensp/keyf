package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.Solution;
import keyf.clueless.State;
import keyf.clueless.TurnManager;
import keyf.clueless.data.Player;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;
import static keyf.util.ParamUtil.requireNonNull;

/**
 *
 * @author justin
 */
public class Accusation implements Action
{
    private final static String HE_WON_MESSAGE 
            = "I was correct! It was {0} in the {1} with the {2}. I won!";

    private final static String GAME_WIN_MESSAGE = "{0} wins the game!";

    private final static String YOU_WON_MESSAGE = "Congraulations! You Won!";

    private final static String YOU_LOST_MESSAGE 
            = "I'm sorry, your accusation was incorrect. You have lost the game";

    private final static String HE_LOST_MESSAGE 
            = "{0} has made a false accusation. He has lost the game.";

    private final Suspect suspect;
    private final Weapon weapon;
    private final Room room;

    public Accusation(Suspect suspect, Weapon weapon, Room room)
    {
        this.suspect = requireNonNull(suspect);
        this.weapon = requireNonNull(weapon);
        this.room = requireNonNull(room);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction(Game game)
    {
        TurnManager turnManager = game.getTurnManager();

        // The player that made the accusation.
        Player currentPlayer = turnManager.getCurrentPlayer();

        // First, determine if the current player has won the game or not.
        Solution solution = new Solution(suspect, weapon, room);

        boolean won = game.getSolution().equals(solution);

        for (Player player : game.getPlayers())
        {
            State.Builder stateBuilder = new State.Builder(
                    game.getLatestState(player));


            if (player.equals(currentPlayer))
            {
                if (won)
                {
                    stateBuilder.setSuspectMessage(YOU_WON_MESSAGE);
                    // No one can do anything anymore.
                    stateBuilder.clearActions();
                }
                else
                {
                    stateBuilder.setSuspectMessage(YOU_LOST_MESSAGE);
                    // Disqualify the current player (they can no longer move, 
                    // make suggestions, but have to answer others' suggestions)
                    // and set the next player.
                    turnManager.disqualifyCurrentPlayer();
                }
            }
            else // all other players
            {
                if (won)
                {
                    stateBuilder.setSuspectMessage(String.format(HE_WON_MESSAGE,
                          suspect, room, weapon));
                    // No one can do anything.
                    stateBuilder.clearActions();
                }
                else
                {
                   stateBuilder.setSuspectMessage(String.format(HE_LOST_MESSAGE,
                           player));
                }
            }

            stateBuilder.setLogMessage(String.format(
                    GAME_WIN_MESSAGE,
                    player));

            game.addState(player, stateBuilder.build());
        }
    }
}