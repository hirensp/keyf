package keyf.clueless.server;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.Game;
import keyf.clueless.action.Move;
import keyf.clueless.data.location.Hallway;
import keyf.clueless.data.location.Location;
import keyf.clueless.data.location.Room;

/**
 *
 * @author justin
 */
public class MoveServlet extends PostListStringServlet
{
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     * <p/>
     * @param request servlet request
     * @param response servlet response
     * <p/>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void completeDoPost(HttpServletRequest request,
                                  HttpServletResponse response,
                                  List<String> bodyParameters)
            throws ServletException, IOException
    {
        String locationString = bodyParameters.get(0);

        Location location;

        if (Room.isValid(locationString))
        {
            location = Room.valueOf(locationString);
        }
        else if (Hallway.isValid(locationString))
        {
            location = Hallway.valueOf(locationString);
        }
        else
        {
            throw new IllegalArgumentException(
                    locationString + " is neither a " + Hallway.class.getName()
                    + " or a " + Room.class.getName());
        }

        Move move = new Move(location);

        Game game = (Game) request.getServletContext().getAttribute(
                ServletContextAttributeKeys.GAME);

        synchronized(game)
        {
            move.performAction(game);
        }
    }
}