package keyf.clueless.action;

import keyf.clueless.action.offer.OfferSuggestion;
import keyf.clueless.action.offer.OfferAccusation;
import keyf.clueless.action.offer.OfferMove;
import keyf.clueless.Board;
import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.TurnManager;
import keyf.clueless.action.offer.OfferEndTurn;
import keyf.clueless.data.Player;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.location.Location;
import keyf.clueless.data.location.Room;

import java.text.MessageFormat;

/**
 * Ends the {@link TurnManager#getCurrentPlayer() current player's} turn.
 *
 * @author justin
 */
public class EndTurn implements Action
{
    private final static String SUSPECT_MESSAGE = "I have ended my turn.";
    private final static String LOG_MESSAGE = "{0} has ended his/her turn.";

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction(Game game)
    {
        TurnManager turnManager = game.getTurnManager();

        Player currentPlayer = turnManager.getCurrentPlayer();
        Player nextPlayer = turnManager.nextPlayer();

        for (Player player : game.getPlayers())
        {
            State.Builder builder = new State.Builder(
                    game.getNewestState(player));

            if (currentPlayer.equals(player))
            {
                builder.clearActions();
            }
            else if (nextPlayer.equals(player))
            {
                builder.clearActions();

                Board board = game.getBoard();

                Suspect suspect = nextPlayer.getSuspect();
                Location currentLocation = board.getLocation(suspect);

                builder.addAction(
                        new OfferMove(board.getAvailableLocations(suspect)));

                // If it is a room (hallways are single occupancy) add the
                // suggest action
                if (currentLocation instanceof Room
                        /* && moved here on another's suggestion */)
                {
                    builder.addAction(
                            new OfferSuggestion((Room) currentLocation));
                }

                builder.addAction(new OfferAccusation());
                builder.addAction(new OfferEndTurn());
            }

            builder.setSuspectMessage(SUSPECT_MESSAGE);
            builder.setLogMessage(MessageFormat.format(
                    LOG_MESSAGE, currentPlayer.getIdentifier()));

            game.addState(player, builder.build());
        }
    }
}