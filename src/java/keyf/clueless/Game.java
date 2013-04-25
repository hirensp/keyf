package keyf.clueless;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import keyf.clueless.data.Player;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.card.Card;

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
     * The board being used to play the game.
     */
    private final Board board;

    /**
     * The people playing this game.
     */
    private final List<Player> players;

    private final TurnManager turnManager;

    private final Solution solution;

    private final Map<Player, State> currentState;

    /**
     * Creates a new game. The true criminal, murder weapon, and room are
     * decided, and all cards are assigned to the {@code players}.
     * 
     * @param identifier Uniquely identifies this game within the server.
     * @param players The players of this game
     */
    public Game(String identifier, Map<String, Suspect> players)
    {
        this.identifier = identifier;
        this.board = new Board();

        // Set aside the solution and deal the rest of the cards to the players.
        CardDealer dealer = new CardDealer();
        this.solution = dealer.getSolution();
        this.players = getPlayers(players, dealer);
        this.turnManager = new TurnManager(this.players);

        this.currentState = new HashMap<Player, State>();
        // TODO initialize current states.
    }

    public Board getBoard()
    {
        return board;
    }

    public List<Player> getPlayers()
    {
        return players;
    }

    public TurnManager getTurnManager()
    {
        return turnManager;
    }

    public State getState(Player player)
    {
        return currentState.get(player);
    }

    public void setState(Player player, State state)
    {
        currentState.put(player, state);
    }

    public Solution getSolution()
    {
        return solution;
    }

    /**
     * Assigns {@link Card}s to each of the {@code prePlayers}, and returns
     * fully formed {@link Player}s. The returned list will have the same order
     * as {@code prePlayers}.
     * 
     * @param prePlayers information needed to form a list of {@link Player}s
     * @param dealer used to assign cards to the returned {@link Player}s
     *
     * @return never {@code null}
     */
    private static List<Player> getPlayers(Map<String, Suspect> prePlayers,
                                           CardDealer dealer)
    {
        // The list of players that will be created
        List<Player> players = new ArrayList<Player>();

        // first, deal the cards to each player

        Map<String, Set<Card>> assignedCards = new HashMap<String, Set<Card>>();

        Iterator<String> playerIterator = prePlayers.keySet().iterator();
        
        while (dealer.hasMore())
        {
            // keep cycling around the players until the dealer is out of cards
            if (!playerIterator.hasNext())
            {
                playerIterator = prePlayers.keySet().iterator();
            }

            String playerId = playerIterator.next();
            Set<Card> playerCards = assignedCards.get(playerId);

            if (playerCards == null)
            {
                // it was not in the map, create a new one.
                playerCards = new HashSet<Card>();
                assignedCards.put(playerId, playerCards);
            }

            playerCards.add(dealer.deal());
        }
                

        return players;
    }
}