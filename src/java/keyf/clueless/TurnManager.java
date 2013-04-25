package keyf.clueless;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import keyf.clueless.data.Player;
import static keyf.util.ParamUtil.*;

/**
 * Manages whose players turn it is, as well as who action is currently waiting
 * on.
 * 
 * @author justin
 */
public class TurnManager
{
    /**
     * Players who no longer have turns, but must still be active in some cases.
     */
    private final Set<Player> disqualifiedPlayers = new HashSet<Player>();

    private WrappingIterator<Player> currentPlayer;

    private WrappingIterator<Player> currentlyActivePlayer;

    /**
     * Manages the turns of the {@code players}
     *
     * @param players the players whose turn is being managed.
     */
    public TurnManager(List<Player> players)
    {
        requireNonNullAndContainsNonNull(players);
        
        this.currentPlayer = new WrappingIterator<Player>(players);
        this.currentlyActivePlayer = new WrappingIterator<Player>(players);
    }

    /**
     * Returns the player whose turn it currently is.
     *
     * @return never {@code null}
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer.current();
    }

    /**
     * Returns the player upon whom action is required (may be the same as
     * "current player" or different, such as when a player must respond to a
     * suggestion).
     *
     * @return never {@code null}
     */
    public Player getCurrentlyActivePlayer()
    {
        return currentlyActivePlayer.current();
    }

    /**
     * Makes the player whose turn it currently is the new player whose turn it
     * is.
     *
     * @return The next player whose turn it now is.
     */
    public Player nextPlayer()
    {
        do
        {
            currentPlayer.next();
        }
        while (!disqualifiedPlayers.contains(currentPlayer.current()));

        return currentPlayer.current();
    }

    /**
     * Makes the player after the currently active player the new active player
     *
     * @return The newly active player
     */
    public Player nextActivePlayer()
    {
        return currentlyActivePlayer.next();
    }
}