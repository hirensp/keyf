<%--
    Document   : index
    Created on : Mar 29, 2013, 12:02:45 AM
    Author     : hp
--%>

<%@page import="keyf.clueless.server.ServletContextAttributeKeys"%>
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

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Welcome to Keyf Clue-Less Board Game</title>
    </head>
    <body>
        <%-- TODO This should be in its own file --%>
        <script>
$(function() {
    $("#PlayerSelection").submit(function(event) {

      /* stop form from submitting normally */
      event.preventDefault();

      /* Send the data using post and put the results in a div */
        $.ajax({
          url: "GameManagerAddingPlayers",
          type: "post",
          data: { name:    $('input[name=name]').val(),
                  suspect: $('input[name=suspect]:checked').val()},
          success: function() {
              elm = document.getElementById('PlayerSelection');
              elm.parentNode.removeChild(elm);
              $("#waiting").text('waiting on other players...');
          },
          error: function() {
              alert("failure");
          }
        });
    });
});
        </script>
        <script>
(function poll() {
    setTimeout(function() {
        $.ajax({
            url: 'PollStartGame',
            type: 'GET',
            success: function(data) {
                if (data.canCreateGame) {
                    $("#waiting").hide();
                    $('#startGame').show();
                }
            },
            error: function(data) {
                //alert("poll failed");
            },
            dataType: 'json',
            // Poll until we are able to create the game.
            complete: poll,
            timeout: 1000
        });
    }, 2000);
})();
        </script>

        <h1>Welcome to Keyf Clue-Less Board Game</h1>
        <form  method="POST" id="PlayerSelection">
            <p>
                <label for="name">Please Enter Your Name:</label>
                <input type="text" name="name" id="name" required="required"/><br/>
                <label for="suspectTable">Please select a suspect:</label>
            </p>
            <table id="suspectTable">
                <%
                    List<Suspect> suspects = Arrays.asList(Suspect.values());
                    Iterator<Suspect> iterator = suspects.iterator();
                    
                    GameManager _manager = (GameManager)request.getServletContext().getAttribute(ServletContextAttributeKeys.GAME_MANAGER);
                    Suspect suspect = iterator.next();
                    
                    // Show all Suspects
                    out.println("<tr align='center'>");
                    while (suspect != null)
                    {                           
                        out.println("<td>");
                        
                        out.println("<img "
                                + "src='images/"+ suspect.name() +".jpg' "
                                + "alt='"+ suspect.name()
                                + "' width='70' height='100'>");
                        
                        out.print("<div style='display:table-cell; vertical-align:bottom; padding:5px;' ><input type='radio' "
                                + "name='suspect' "
                                + "value='"+ suspect.name() +"' ");
                        
                        if (_manager.checkCurrentSuspect(suspect)){
                            out.print(" disabled='disabled' > </div>");
                        }
                        else{
                            out.print("></div>");
                        }
                        
                        out.println("</td>");
                        
                        if(iterator.hasNext())
                            suspect = iterator.next();
                        else
                            suspect = null;
                    }
                    out.println("</tr>");
                %>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Select Suspect" />
                    </td>
                </tr>
            </table>
        </form>
        <p id="waiting"></p>
        <div id="startGame" style="display: none">
            <form action="StartGame" method="POST">
                <input type="submit" value="Start Game" />
            </form>
        </div>
    </body>
</html>