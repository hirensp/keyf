package keyf.clueless.server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.Game;
import keyf.clueless.GameManager;
import keyf.clueless.data.Item;
import keyf.clueless.data.Player;

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

        StringBuilder stringBuilder = new StringBuilder();

        synchronized (game)
        {
            // TODO form message.
        }
        
        out.write(stringBuilder.toString());
    }

    /**
     * Returns a string of HTML describing the {@code item} as a card. This
     * appears in the list of cards.
     *
     * Example return value:
     * <div id="RopeCard">
     *     <img src="imges/Rope.jpg" alt="Rope" hight="155" width="100" />
     * </div>
     *
     * @param item The item to make the HTML string for.
     *
     * @return never {@code null}
     */
    private String getCardHtml(Item item)
    {
        return "";
    }

    /**
     * Returns a string of HTML describing a player. This appears at the top of
     * the game board (on the game.html page).
     *
     * Example return value (I don't know if this is exactly what you'll need,
     * but I'll try to get the piping all in place so you can test):
     * <div id="player.getIdentifier()">
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
        return "";
    }
}