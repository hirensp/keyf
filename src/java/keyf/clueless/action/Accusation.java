package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;

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
        this.suspect = suspect;
        this.weapon = weapon;
        this.room = room;
    }
    
    @Override
    public void performAction(Game game)
    {
        //Solution solution = new Solution(suspect, weapon, room);

//        if (game.getSolution().equals(solution))
//        {
//            // we win the game!!!
//        }
//        else
//        {
//            // the state of the current player is affected somehow....
//        }
    }

}
