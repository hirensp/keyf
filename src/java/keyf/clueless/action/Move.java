package keyf.clueless.action;

import keyf.clueless.Game;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.location.Location;

/**
 *
 * @author justin
 */
public class Move implements Action
{
    private final Suspect suspect;

    private final Location location;
    
    public Move(Suspect suspect, Location location)
    {
        this.suspect = suspect;
        this.location = location;
    }

    @Override
    public void performAction(Game game)
    {
        game.getBoard().setLocation(suspect, location);
    }
}
