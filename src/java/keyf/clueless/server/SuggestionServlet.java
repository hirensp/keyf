package keyf.clueless.server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.Game;
import keyf.clueless.action.Suggestion;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Hallway;
import keyf.clueless.data.location.Location;
import keyf.clueless.data.location.Room;

/**
 *
 * @author jonathanpomper
 */
public class SuggestionServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext servletContext=request.getServletContext();
        Game game = (Game) servletContext.getAttribute(ServletContextAttributeKeys.GAME); 
        synchronized(game)
        {
            String roomString=request.getParameter("Room");
            String weaponString=request.getParameter("Weapon");
            String suspectString=request.getParameter("Suspect");
            
            Weapon weapon=Weapon.valueOf(weaponString);
            Suspect suspect=Suspect.valueOf(suspectString);
            Room room= Room.valueOf(roomString);
  
            Suggestion suggestion= new Suggestion(suspect, weapon, room);
            suggestion.performAction(game);
        }
        
    request.getRequestDispatcher("Suggestion.jsp").forward(request, response);

    }
}
    