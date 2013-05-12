package keyf.clueless.server;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.Game;
import keyf.clueless.action.Refute;
import keyf.clueless.data.Item;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;

/**
 *
 * @author justin
 */
public class RefuteServlet extends PostListStringServlet
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
        Item item = null;

        for (String parameter : bodyParameters)
        {
            if (Suspect.isValid(parameter))
            {
                item = Suspect.valueOf(parameter);
            }

            if (Weapon.isValid(parameter))
            {
                item = Weapon.valueOf(parameter);
            }

            if (Room.isValid(parameter))
            {
                item = Room.valueOf(parameter);
            }
        }

        Refute refute = new Refute(item);

        Game game = (Game) request.getServletContext().getAttribute(
                ServletContextAttributeKeys.GAME);

        synchronized(game)
        {
            refute.performAction(game);
        }
    }
}