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
    private final String HE_WON_MESSAGE;
    private final String GAME_WIN_MESSAGE;
    private final String YOU_WON_MESSAGE;
    private final String YOU_LOST_MESSAGE;
    private final String HE_LOST_MESSAGE;
    private final String GAME_LOSS_MESSAGE;

    public Accusation(Suspect suspect, Weapon weapon, Room room)
    {
        this.suspect = requireNonNull(suspect);
        this.weapon = requireNonNull(weapon);
        this.room = requireNonNull(room);
        this.HE_WON_MESSAGE= "I was correct! It was {0} in the {1} with the {2}."+
                "I won!";
        this.GAME_WIN_MESSAGE = "{0} wins the game!";
        this.YOU_WON_MESSAGE="Congraulations! You Won!";
        this.YOU_LOST_MESSAGE="I'm sorry, your accusation was incorrect. You have"+
                "lost the game";
        this.HE_LOST_MESSAGE="{0} has made a false accusation. He has lost the game.";
        this.GAME_LOSS_MESSAGE="{0} has made a false accusation. He has lost the game.";
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
                        State.Builder stateBuilder
                                =new State.Builder(game.getState(player));
                        //just trying to follow how Justin did it. Don't really 
                        //know how it the String.format should be done... 
                        stateBuilder.setSuspetMessage(String.format(YOU_WON_MESSAGE));
                        stateBuilder.setLogMessage(String.format(GAME_WIN_MESSAGE, 
                                player));
                        game.setState(player, stateBuilder.build());
                        turnManager.disqualifyCurrentPlayer();
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
                    State.Builder stateBuilder
                          =new State.Builder(game.getState(player));
                    //just trying to follow how Justin did it. Don't really 
                    //know how it the String.format should be done... 
                    stateBuilder.setSuspetMessage(String.format(HE_WON_MESSAGE, 
                          suspect, room, weapon));
                    stateBuilder.setLogMessage(String.format(GAME_WIN_MESSAGE, 
                          player));
                    game.setState(player, stateBuilder.build());
                    turnManager.disqualifyCurrentPlayer();
                }
                else
                {
                   State.Builder stateBuilder
                           =new State.Builder(game.getState(player));
                   //just trying to follow how Justin did it. Don't really 
                   //know how it the String.format should be done... 
                   stateBuilder.setSuspetMessage(String.format(HE_LOST_MESSAGE, 
                           player));
                   stateBuilder.setLogMessage(String.format(GAME_LOSS_MESSAGE, 
                           player));
                   game.setState(player, stateBuilder.build());
                }
            }
        }
    }
}