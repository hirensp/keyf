package keyf.clueless.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class SuggestionServlet extends HttpServlet
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
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException
    {
        Game game = (Game) request.getServletContext().getAttribute(
                ServletContextAttributeKeys.GAME);

        synchronized(game)
        {
            Suggestion suggestion= new Suggestion(
                    Suspect.valueOf(request.getParameter("Suspect")),
                    Weapon.valueOf(request.getParameter("Weapon")),
                    Room.valueOf(request.getParameter("Room")));

            suggestion.performAction(game);
        }
        
        request.getRequestDispatcher("Suggestion.jsp")
                .forward(request, response);
    }
}
    