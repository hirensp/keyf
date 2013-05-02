package keyf.clueless.server;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class MoveServlet extends HttpServlet
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
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext servletContext = request.getServletContext();
        Game game = (Game) servletContext.getAttribute("GAME");

        synchronized(game)
        {
            String locationString = request.getParameter("Location");
            Location location = null;

            try
            {
                location = Room.valueOf(locationString);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            if (location == null)
            {
                location = Hallway.valueOf(locationString);
            }

            Move move = new Move(location);
            move.performAction(game);
        }

        request.getRequestDispatcher("Move.jsp").forward(request, response);
    }

    /**
     * Returns a short description of this servlet.
     * <p/>
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Handles a Move request";
    }
}
