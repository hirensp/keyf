<%-- 
    Document   : index
    Created on : Mar 29, 2013, 12:02:45 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Keyf Clue-Less Board Game</title>
    </head>
    <body><form name="Select_Player" method="POST">
        
        <h1>Welcome to Keyf Clue-Less Board Game</h1>
        <h2>Please select a player.  </h2>
        <table>
            <tr>
                <th>Select</th>
                <th>Player Name</th>
            </tr>
            <tr>
                <td><input type="radio" name="player" value="1" /> </td>
                <td><img src="" alt="Misses Scalet"/></td>
            </tr>
            <tr>
                <td><input type="radio" name="player" value="2" /></td>
                <td><img src="" alt="Miss Scalet"/></td>                
            </tr>
            <tr>
                <td> <input type="radio" name="player" value="3" /></td>
                <td><img src="" alt="Misses Scalet"/></td>                
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="btnSubmit" name="Submit_Form" />
                </td>
            </tr>
        </table>

</form>
    </body>
</html>

