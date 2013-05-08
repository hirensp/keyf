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
     *     "players": [ {player info...}, ... ],
     *     "cards": [ {card info...}, ...]
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
            json.accumulate("players", getPlayerJsonString(player));
        }

        for (Item card : cards)
        {
            json.accumulate("cards", getCardJsonString(card));
        }

        String jsonString = json.toString();
        out.write(json.toString());
    }

    /**
     * Returns a JSON string that contains information about the card (image)
     * @param item The item to make the JSON string for.
     * {
     *     "image": "images/COL_MUSTARD.jpg",
     *     "height": 155,
     *     "width": 100
     * }
     * @return never {@code null}
     */
    private String getCardJsonString(Item item)
    {
        JSONObject jsonCard = new JSONObject();
        String description = item.getDescription();
        jsonCard.put("image", "images/"+description+".jpg")
                .put("height", 155)
                .put("width", 100);

        return jsonCard.toString();
    }

    /**
     * Returns a JSON string that contains information about each player
     * (picture and name)
     * {
     *     "image": "images/COL_MUSTARD.jpg",
     *     "height": 155,
     *     "width": 100,
     *     "name": Kuporific
     * }
     *
     * @param player The player to make the JSON string for.
     *
     * @return never {@code null}.
     */
    private String getPlayerJsonString(Player player)
    {
        JSONObject jsonPlayer = new JSONObject();
        String suspect = player.getSuspect().getDescription();
        jsonPlayer.put("image", "images/"+suspect+".jpg")
                .put("height", 155)
                .put("width", 100)
                .put("name", player.getIdentifier());

        return jsonPlayer.toString();
    }

    // HINT!!!!
    private JSONObject getImageInformation()
    {
        return null;
    }
}