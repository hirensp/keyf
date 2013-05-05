<%-- 
    Document   : index
    Created on : Mar 29, 2013, 12:02:45 AM
    Author     : hp
--%>

<%@page import="keyf.clueless.data.Suspect"%>
<%@page import="keyf.clueless.data.Item"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
    <head>
        <%@ page 
        language="java"
        contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"
        import="java.util.*, keyf.clueless.*"
        %>
        
        <%-- This gives us jQuery and must be at the top of all our pages that use jQuery --%>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Keyf Clue-Less Board Game</title>
    </head>
    <body>
        <%-- TODO This should be in its own file --%>
        <script>
$(function() {
    $('#PlayerSelection').on('submit', function(e) {
        $.ajax('clueless/GameManagerServlet', {
            dataType: 'json',
            data: { name:    $('input[name=name]').val(),
                    suspect: $('radio[name=name]').val()}})
        .done(function(data) {
            elm = document.getElementById('#PlayerSelection');
            elm.parentNode.removeChild(elm);
        });
        return false;
    });
});
        </script>

        <h1>Welcome to Keyf Clue-Less Board Game</h1>
        <form method="POST" id="PlayerSelection">
            <p>
                Please Enter Your Name: <input type="text" name="name" required="required"/>
            </p>
            <p>Please select a suspect:</p>
            <table>
                <%
                    List<Suspect> suspects = Arrays.asList(Suspect.values());
                    Iterator<Suspect> iterator = suspects.iterator();

                    // Show all Suspects
                    out.println("<tr>");
                    while (iterator.hasNext())
                    {
                        Suspect suspect = iterator.next();
                        out.println("<td>");
                        out.println("<img "
                                + "src='images/"+ suspect.name() +".jpg' "
                                + "alt='"+ suspect.name()
                                + "' width='70' height='100' >");
                                out.println("</td>");
                    }
                    out.println("</tr>");

                    // generate an iterator that will be the same order
                    iterator = suspects.iterator();
                    // show the radio buttons
                    out.println("<tr>");
                    while (iterator.hasNext())
                    {
                        out.println("<td>");
                        out.print("<input type='radio' "
                                + "name='suspect' "
                                + "value='"+ iterator.next().name() +"'>");
                        out.println("</td>");
                    }
                    out.println("</tr>");
                %>
                <tr>
                    <td colspan="2">
                        <input type="submit" method="POST" value="Select Suspect" />
                    </td>
                </tr>
            </table>
        </form>
                <form id="Start_Game" method="POST" action="GameManager">
                    <input type="submit" value="Start Game" name="btnStart" />
                </form>
    </body>
</html>