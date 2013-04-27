package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.data.Player;
import static keyf.util.ParamUtil.*;

/**
 *
 * @author justin
 */
public class EndTurn implements Action
{
    private final Player playerEndingTurn;

    /**
     *
     * @param playerEndingTurn The player who has elected to end his/her turn.
     */
    public EndTurn(Player playerEndingTurn)
    {
        this.playerEndingTurn = requireNonNull(playerEndingTurn);
    }
    
    @Override
    public void performAction(Game game)
    {
        for (Player player : game.getPlayers())
        {
            if (player.equals(playerEndingTurn))
            {

            }
            else
            {

            }
        }

    }

}
