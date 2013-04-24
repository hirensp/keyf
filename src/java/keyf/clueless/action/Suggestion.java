package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;

/**
 *
 * @author justin
 */
public class Suggestion implements Action
{
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
    }
}