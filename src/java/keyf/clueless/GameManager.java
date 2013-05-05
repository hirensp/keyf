package keyf.clueless;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import keyf.clueless.data.Suspect;

/**
 * Manages the requirements to start a game: 3 to 6 connected clients, each
 * having a name and a {@link Suspect}. Once the requirements have been met,
 * this class is also responsible for starting a game.
 * <p/>
 * Note: the "main page" HttpServlete should be accessing this class. It should
 * be created by the GameManagerInitializer (which will create it on server
 * startup).
 * <p/>
 * (Currently, we're only required to have one running game at a time.)
 * @author justin
 */
public class GameManager
{
    /**
     * Holds the client data (Name, Suspect) before a Game is formed.
     * In the order that a client submitted his/her PrePlayer data.
     */
    private final List<PrePlayer> prePlayers = new LinkedList<PrePlayer>();

    public boolean canCreateGame()
    {
        return prePlayers.size() > 3;
    }

    public void addClientData(String name, Suspect suspect)
    {
        prePlayers.add(new PrePlayer(name, suspect));
    }

    public void removeClientData(String name)
    {
        // TODO - We could define equals() in PrePlayer and make a new PrePlayer
        //        then remove it from the List but that seems weird.
    }

    public Game createGame()
    {
        // double check that we can actually make a Game.
        if (canCreateGame())
        {
            // canCreateGame() insures we have at least 3 players.
            // If there are less than six, start the game with that number of
            //     players.
            // Otherwise, start the game with 6 players (0 through 5)
            int numberOfPlayers = prePlayers.size() <= 6
                                  ? prePlayers.size() - 1 : 5;

            List<PrePlayer> gamePlayers = new ArrayList<PrePlayer>(
                    prePlayers.subList(0, numberOfPlayers));

            // remove these players from the prePlayers list since they are now
            // Players!
            prePlayers.removeAll(gamePlayers);

            // start the game!
            return new Game("GAME", gamePlayers);
        }
        else
        {
            throw new IllegalStateException(
                    "Verify canMakeGame() before calling createGame()");
        }
    }
}
