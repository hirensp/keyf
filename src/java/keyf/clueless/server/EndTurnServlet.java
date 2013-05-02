package keyf.clueless.server;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.Game;
import keyf.clueless.action.EndTurn;

/**
 *
 * @author justin
 */
public class EndTurnServlet extends HttpServlet
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
        Game game = (Game) servletContext.getAttribute(
                ServletContextAttributeKeys.GAME);

        synchronized(game)
        {
            EndTurn endTurn = new EndTurn();
            endTurn.performAction(game);
        }

        request.getRequestDispatcher("EndTurn.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * <p/>
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}
