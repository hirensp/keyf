package keyf.clueless.server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.GameManager;

/**
 * The start page polls constantly until the game is able to start. This returns
 * whether or not the {@link Game} is ready to start.
 *
 * @author Justin
 */
public class PollStartGameServlet extends HttpServlet
{
    /**
     * Returns a JSON object that answers whether or not the game is ready to
     * start:
     * { "canCreateGame": true/false }
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        GameManager gameManager = (GameManager) request.getServletContext()
                .getAttribute(ServletContextAttributeKeys.GAME_MANAGER);

        synchronized (gameManager)
        {
            out.write("{ \"canCreateGame\": " + gameManager.canCreateGame() + " }");
        }
    }
}