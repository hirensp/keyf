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
    <body><form name="Select_Player" method="POST" action="Character">
        
        <h1>Welcome to Keyf Clue-Less Board Game</h1>
        <h2>Please select a player.  </h2>
        <table>
            <tr>
                <th>Select</th>
                <th>Player Name</th>
            </tr>
            
            <%
            //ArrayList al = new Suspect.values();
                //for(Suspect suspect : Suspect.values()){
                //    suspect.compareTo(Suspect.COL_MUSTARD);
                //}
            
               // Array selectedCards []  = Suspect.values();
            
               //GameManager gManage = new GameManager();
               //gManage.createGame();
               //gManage.addClientData(name, Suspect.);
                        
            for(Suspect s : Suspect.values()){                
                out.println("<tr>");
                    out.println("<td><input type='radio' name='character' value='"+ s.name() +"'> </td> ");
                    out.println("<td><img src='images/"+ s.name() +".jpg' alt='"+ s.name() +"' width='70' height='100' ></td>");                    out.println("</td>");
                out.println("</tr>");
            }
                
               
            %>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Play" name="btnPlay" />
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Start Game" name="btnStart"
                </td>
            </tr>
        </table>
        <h3>
            Please Enter Your Name: <input type="text" name="player_name">
        </h3>
</form>
    </body>
</html>

