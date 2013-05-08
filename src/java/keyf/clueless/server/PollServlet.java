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
import keyf.clueless.action.offer.OfferAction;
import keyf.clueless.action.offer.OfferMove;
import keyf.clueless.data.Player;
import keyf.clueless.data.location.Location;
import org.json.JSONArray;
import org.json.JSONObject;

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
        response.setContentType("application/json");

        Game game = (Game) request.getServletContext().getAttribute(
                ServletContextAttributeKeys.GAME);

        String currentPlayerName= (String) request.getSession()
                .getAttribute(ServletContextAttributeKeys.SESSION_PLAYER_ID);

        State state = null;

        synchronized(game)
        {
            // Get the current player's state
            state = game.getLatestState(
                    game.getPlayerByName(currentPlayerName));
        }

        JSONObject json = new JSONObject();
        json.put("suspectMessage", state.getSuspectMessage());
        json.put("logMessage", state.getLogMessage());

        for (OfferAction action : state.getAvailableActions())
        {
            json.accumulate("actions", action.getJsonString());
        }
        // TODO more stuff about which players moved and weapons too
    }
}