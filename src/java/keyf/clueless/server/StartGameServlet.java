/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package keyf.clueless.server;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.GameManager;

/**
 * Responsible for starting the {@link Game}.
 *
 * @author justin
 */
public class StartGameServlet extends HttpServlet
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
        ServletContext context = request.getServletContext();

        GameManager gameManager = (GameManager)
                context.getAttribute(ServletContextAttributeKeys.GAME_MANAGER);

        synchronized (gameManager)
        {
            if (gameManager.canCreateGame())
            {
                context.setAttribute(
                        ServletContextAttributeKeys.GAME,
                        gameManager.createGame());
            }
        }

        // forward to the Game page servlet.
        request.getRequestDispatcher("play.html").forward(request, response);
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
    }
}