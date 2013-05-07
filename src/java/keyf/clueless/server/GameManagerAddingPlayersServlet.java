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
import keyf.clueless.GameManager;
import keyf.clueless.data.Suspect;

/**
 *
 * @author justin
 */
public class GameManagerAddingPlayersServlet extends HttpServlet
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
        GameManager gameManager = (GameManager) request.getServletContext()
                .getAttribute(ServletContextAttributeKeys.GAME_MANAGER);

        String name = (String) request.getParameter("name");

        request.getSession().setAttribute(
                ServletContextAttributeKeys.SESSION_PLAYER_ID,
                name);
        
        synchronized (gameManager)
        {
            gameManager.addClientData(
                    name,
                    Suspect.valueOf((String) request.getParameter("suspect")));
        }
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
