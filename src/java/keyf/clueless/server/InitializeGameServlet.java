package keyf.clueless.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.Game;
import keyf.clueless.data.Item;
import keyf.clueless.data.Player;
import org.json.JSONObject;

/**
 * The first Servlet to serve clients with the Game page.
 * @author Justin
 */
public class InitializeGameServlet extends HttpServlet
{
    /**
     * Returns a json object in the following manner:
     * {
     *     "players": [ #html#, #html, ... ],
     *     "cards": [#html, #html, ...]
     * }
     *
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        Game game = (Game) request.getServletContext()
                .getAttribute(ServletContextAttributeKeys.GAME);

        List<Player> players;
        Set<? extends Item> cards;

        // synchronize for as short of a time as possible
        synchronized (game)
        {
            players = game.getPlayers();

            String playerId = (String) request.getSession().getAttribute(
                            ServletContextAttributeKeys.SESSION_PLAYER_ID);

            Player currentPlayer = game.getPlayerByName(playerId);
            
            cards = currentPlayer.getCards();
        }

        JSONObject json = new JSONObject();

        for (Player player : players)
        {
            json.accumulate("players", getPlayerHtml(player));
        }

        for (Item card : cards)
        {
            json.accumulate("cards", getCardHtml(card));
        }

        String jsonString = json.toString();
        out.write(json.toString());
    }

    /**
     * Returns a string of HTML describing the {@code item} as a card. This
     * appears in the list of cards.
     *
     * Example return value:
     * <div id="RopeCard" style="float: left">
     *     <img src="imges/Rope.jpg" alt="Rope" hight="155" width="100" />
     * </div>
     *
     * @param item The item to make the HTML string for.
     *
     * @return never {@code null}
     */
    private String getCardHtml(Item item)
    {
        return "<div id=\"RopeCard\" style=\"float: left\">"
               + "<img src=\"images/Rope.jpg\" alt=\"Rope\" hight=\"155\" width=\"100\" />"
                + "</div>";
    }

    /**
     * Returns a string of HTML describing a player. This appears at the top of
     * the game board (on the game.html page).
     *
     * Example return value (I don't know if this is exactly what you'll need,
     * but I'll try to get the piping all in place so you can test):
     * <div id="player.getIdentifier()" style="float: left">
     *     <img src="images/COL_MUSTARD.jpg" alt="Col Mustard" hight="155" width="100" />
     *     <p>
     *         Player ID (name) from player.getIdentifier().
     *     </p>
     * </div>
     *
     * @param player The player to make the HTML string for.
     *
     * @return never {@code null}.
     */
    private String getPlayerHtml(Player player)
    {
        return "<div id=\"" + player.getIdentifier() + "\" style=\"float: left\">"
               + "<img src=\"images/COL_MUSTARD.jpg\" alt=\"Col Mustard\" hight=\"155\" width=\"100\" />"
               + "<p>" + player.getIdentifier() + "</p>"
                + "</div>";
    }
}