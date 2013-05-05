package keyf.clueless;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import keyf.clueless.data.Item;
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
     * The board being used to play the game.
     */
    private final Board board;

    /**
     * The people playing this game.
     */
    private final List<Player> players;

    /**
     * Manages the turns of this Game's players.
     */
    private final TurnManager turnManager;

    /**
     * The solution to this Game.
     */
    private final Solution solution;

    /**
     * Holds each player's State, where the top of the queue is the oldest
     * state, and the bottom the newest. Will always hold at least one (the
     * latest) State.
     */
    private final Map<Player, Deque<State>> currentState;

    /**
     * Creates a new game. The true criminal, murder weapon, and room are
     * decided, and all cards are assigned to the {@code players}.
     * 
     * @param identifier Uniquely identifies this game within the server.
     * @param players The players of this game in turn order
     */
    public Game(String identifier, List<PrePlayer> players)
    {
        this.identifier = identifier;
        this.board = new Board();

        // Set aside the solution and deal the rest of the cards to the players.
        CardDealer dealer = new CardDealer();
        this.solution = dealer.getSolution();
        this.players = getPlayers(players, dealer);
        this.turnManager = new TurnManager(this.players);
        this.currentState = new HashMap<Player, Deque<State>>();
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

    /**
     * Returns the {@link Player} with the given {@code name} or {@code null} if
     * there is no {@link Player} with that name.
     *
     * @param name The {@link Player#getName()} of the desired player
     *
     * @return A Player or {@code null}
     */
    public Player getPlayerByName(String name)
    {
        Player desiredPlayer = null;

        for (Player player : players)
        {
            if (player.getIdentifier().equals(name))
            {
                desiredPlayer = player;
                break;
            }
        }

        return desiredPlayer;
    }
    
    public TurnManager getTurnManager()
    {
        return turnManager;
    }

    /**
     * Returns the oldest {@link State} of the given {@code player}.
     * <p/>
     * This is intended to be called by the {@link PollServlet} used to send
     * messages back to the clients
     *
     * @param player The player whose state is requested.
     *
     * @return the latest {@link State} of the {@code player}, never {@code
     *     null}
     */
    public State getLatestState(Player player)
    {
        State state;

        if (currentState.get(player).size() == 1)
        {
            // get and does NOT remove
            state = currentState.get(player).getFirst();
        }
        else // > 1
        {
            // get and remove
            state = currentState.get(player).pollFirst();
        }

        return state;
    }

    /**
     * Returns the newest {@link State} of the given {@code player}.
     * <p/>
     * This is intended to be called by the {@link Action} classes.
     *
     * @param player The player whose state is requested.
     *
     * @return the newest {@link State} of the {@code player}, never {@code
     *     null}.
     */
    public State getNewestState(Player player)
    {
        return currentState.get(player).getLast();
    }

    /**
     * Adds the {@code state} to the end of the queue of {@link State}s for the
     * given {@code player}.
     * 
     * @param player the player whose state is updated
     * @param state the state of that player
     */
    public void addState(Player player, State state)
    {
        currentState.get(player).addLast(state);
    }

    public Solution getSolution()
    {
        return solution;
    }

    /**
     * Assigns {@link Item}s to each of the {@code prePlayers}, and returns
     * fully formed {@link Player}s. The returned list will have the same order
     * as {@code prePlayers}.
     * 
     * @param prePlayers information needed to form a list of {@link Player}s
     * @param dealer used to assign {@codeItem}s to the returned {@link Player}s
     *
     * @return never {@code null}
     */
    private static List<Player> getPlayers(List<PrePlayer> prePlayers,
                                           CardDealer dealer)
    {
        // The list of players that will be created
        List<Player> players = new ArrayList<Player>();

        // first, deal the cards to each player

        Map<PrePlayer, Set<Item>> assignedCards
                = new HashMap<PrePlayer, Set<Item>>();

        WrappingIterator<PrePlayer> prePlayerIterator
                = new WrappingIterator<PrePlayer>(prePlayers);
        
        while (dealer.hasMore())
        {
            PrePlayer prePlayer = prePlayerIterator.next();
            Set<Item> playerCards = assignedCards.get(prePlayer);

            if (playerCards == null)
            {
                // it was not in the map, create a new one.
                playerCards = new HashSet<Item>();
                assignedCards.put(prePlayer, playerCards);
            }

            playerCards.add(dealer.deal());
        }

        // Finally, create all the players (in turn order)
        for (PrePlayer prePlayer : prePlayers)
        {
            players.add(
                    new Player(
                            prePlayer.getName(),
                            prePlayer.getSuspect(),
                            assignedCards.get(prePlayer)));
        }
                
        return players;
    }
}