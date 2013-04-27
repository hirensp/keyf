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
            if (player.equals(currentPlayer))
            {
                if (won)
                {
                    // TODO win the game!!!
                }
                else
                {

                    State.Builder stateBuilder = new State.Builder(
                            game.getState(turnManager.getCurrentPlayer()));

                    stateBuilder.clearActions();

                    // Disqualify the current player (they can no longer move, make
                    // suggestions, but have to answer others' suggestions) and set the
                    // next player.
                    turnManager.disqualifyCurrentPlayer();
                }
            }
            else
            {
                if (won)
                {
                    // condolences to other players
                }
                else
                {

                }
            }
        }
    }
}