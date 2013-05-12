package keyf.clueless.server;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.Game;
import keyf.clueless.action.Accusation;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;

/**
 *
 * @author jonathanpomper
 */
public class AccusationServlet extends PostListStringServlet
{
    @Override
    protected void completeDoPost(HttpServletRequest request,
                                  HttpServletResponse response,
                                  List<String> bodyParameters)
            throws ServletException,
                   IOException
    {
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

        Accusation accusation = new Accusation(suspect, weapon, room);

        Game game = (Game) request.getServletContext().getAttribute(
                ServletContextAttributeKeys.GAME);

        synchronized(game)
        {
            accusation.performAction(game);
        }
    }
}