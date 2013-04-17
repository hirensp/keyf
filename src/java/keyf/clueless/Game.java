package keyf.clueless;

import java.util.List;
import keyf.clueless.data.Player;

/**
 * Represents a single game of Clue-Less.
 *
 * @author Justin
 */
public class Game
{
    /**
     * A way to uniquely identify this game (TODO make NOT a String)
     */
    private final String identifier;

    /**
     * The people playing this game.
     */
    private final List<Player> players;

    /**
     * The board being used to play the game.
     */
    private final Board board;

    public Game(String identifier, List<Player> players)
    {
        this.identifier = identifier;
        this.players = players;
        this.board = new Board();
    }

    public Board getBoard()
    {
        return board;
    }

    public List<Player> getPlayers()
    {
        return players;
    }
}