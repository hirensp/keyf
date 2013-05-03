/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package keyf.clueless.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import keyf.clueless.GameManager;
import keyf.clueless.data.Player;
import keyf.clueless.data.Suspect;

/**
 *
 * @author hp
 */
@WebServlet(name = "CharacterServlet", urlPatterns = {"/Character"})
public class CharacterServlet extends HttpServlet {

//    /**
//     * Processes requests for both HTTP
//     * <code>GET</code> and
//     * <code>POST</code> methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CharacterServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CharacterServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //response.sendError(500, "Select a character.");
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        Suspect _suspect = Suspect.valueOf(request.getParameter("character"));
        String name = request.getParameter("player_name");
        //instantiate player and assign card 
        if (!_suspect.name().equals(null)){
            GameManager _manager = new GameManager();
            _manager.addClientData(name, _suspect);
            response.sendRedirect("play.jsp");
        }
        else{
//            request.setAttribute("Error", "Please make a valid character selection.");
//            System.out.println("alert(\"Error\", \"Please make a valid character selection.\")");
//            RequestDispatcher dispach = request.getRequestDispatcher("index.jsp");
//            dispach.forward(request, response);
            response.sendRedirect("index.jsp");
        
            
        }
        
        
        
        
        //processRequest(request, response);
    }

//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
}
