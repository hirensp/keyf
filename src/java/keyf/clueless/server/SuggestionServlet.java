package keyf.clueless.server;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.Game;
import keyf.clueless.action.Suggestion;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;

/**
 *
 * @author jonathanpomper
 */
public class SuggestionServlet extends PostListStringServlet
{
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void completeDoPost(HttpServletRequest request,
                                  HttpServletResponse response,
                                  List<String> bodyParameters)
             throws ServletException, IOException
    {
        Game game = (Game) request.getServletContext().getAttribute(
                ServletContextAttributeKeys.GAME);

        Suspect suspect = null;
        Weapon weapon = null;
        Room room = null;

        for (String parameter : bodyParameters)
        {
            if (Suspect.isValid(parameter))
            {
                suspect = Suspect.valueOf(parameter);
            }

            if (Weapon.isValid(parameter))
            {
                weapon = Weapon.valueOf(parameter);
            }

            if (Room.isValid(parameter))
            {
                room = Room.valueOf(parameter);
            }
        }

        Suggestion suggestion = new Suggestion(suspect, weapon, room);

        synchronized(game)
        {
            suggestion.performAction(game);
        }

        // todo forward to the poller
    }
}