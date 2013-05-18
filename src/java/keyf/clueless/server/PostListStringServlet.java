package keyf.clueless.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 * A servlet that recieves a JSON array in the body of the POST message. This
 * servlet is* not intended to be called directly by clients, and is therefore
 * not in* web.xml
 *
 * @author Justin
 */
abstract class PostListStringServlet extends HttpServlet
{
    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        completeDoPost(request, response,
                       parseBody(getBody(request.getReader())));
    }

    protected abstract void completeDoPost(HttpServletRequest request,
                                           HttpServletResponse response,
                                           List<String> bodyParameters)
            throws ServletException, IOException;

    /**
     * Returns the contents of the body of the PUT message. The message will
     * look something like this:
     * "Accuseoption=COL_MUSTARD&Accuseoption=KNIFE&Accuseoption=STUDY"
     * with all those = and &
     *
     * @param reader
     * @return
     * @throws IOException
     */
    private String getBody(BufferedReader reader) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();

        String line = reader.readLine();

        while (line != null)
        {
            stringBuilder.append(line);
            line = reader.readLine();
        }

        return stringBuilder.toString();
    }

    /**
     * Remove the = and & and Accueoptions and what have you and extract the
     * parameters.
     *
     * @param body
     * @return
     */
    private List<String> parseBody(String body)
    {
        List<String> parameters = new ArrayList<String>();

        JSONArray array = new JSONArray(body);

        for (int i = 0; i < array.length(); i++)
        {
            parameters.add(array.getString(i));
        }
        
        return parameters;
    }
}