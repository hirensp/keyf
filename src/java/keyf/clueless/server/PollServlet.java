/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package keyf.clueless.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.data.Player;

/**
 *
 * @author justin
 */
public class PollServlet extends HttpServlet
{
    /**
     * Returns a JSON object that describes the state of the current player
     * (that is, the player whose Name is in the Session).
     *
     * {
     *     "suspectMessage": "I shot the sheriff",
     *     "logMessage": "But Bob Marley did not shoot the deputy",
     *     // All of the following actions are optional...
     *     "move": ["STUDY", ...]"
     *     "suggest": {"weapons": [ "ROPE" ], "suspects": ["COL_MUSTARD", ... ]},
     *     "accuse": {"weapons": [ "ROPE" ], "suspects": ["COL_MUSTARD", ... ], "rooms", [ "STUDY", ...]},
     *
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException
    {
        Game game = (Game) request.getServletContext().getAttribute(
                ServletContextAttributeKeys.GAME);

        HttpSession session = request.getSession();

        synchronized(game)
        {
            // The Player that's polling
            Player player  = game.getPlayerByName(
                    (String) session.getAttribute("name"));

            // Get this player's state.
            State state = game.getLatestState(player);

            response.setContentType("application/json");
        }
    }
}
