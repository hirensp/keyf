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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Keyf Clue-Less Board Game</title>
    </head>
    <body>
        <h1>Welcome to Keyf Clue-Less Board Game</h1>
        <form name="Select_Player" method="POST" action="Character">
            <h3>
                Please Enter Your Name: <input type="text" name="player_name" required="required"/>
            </h3>
            <h2>Please select a suspect.</h2>
            <table>
                <tr>
                    <th>Select</th>
                    <th>Suspect</th>
                </tr>
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

                    // show the radio buttons
                    out.println("<tr>");
                    for(Suspect s : Suspect.values())
                    {
                        out.println("<td>");
                        out.print("<input type='radio' "
                                + "name='character' "
                                + "value='"+ s.name() +"'>");
                        out.println("</td>");
                    }
                    out.println("</tr>");
                %>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Play" name="btnPlay" />
                    </td>
                </tr>
            </table>
        </form>
                <form name="Start_Game" method="POST" action="GameManager">
                    <input type="submit" value="Start Game" name="btnStart" />
                </form>
    </body>
</html>