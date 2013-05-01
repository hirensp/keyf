<%-- 
    Document   : play
    Created on : Apr 5, 2013, 12:18:52 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <style type="text/css">
        .style1
        {
            width: 1100px;
            height: 650px;
            }
        .style2
        {
            width:auto;
            vertical-align: top;
        }
        .style3
        {
            height: 80px;
        }
        .style4
        {
            height: 120px;
            width: 300px;
        }
        .style6
        {
            height:60px;
        }
    </style>
</head>
<body>
    <table class="style1" border="1px" frame="box">
        <tr>
            <td colspan="2" class="style6" style=" background-color:#ccffcc">
                Player&#39;s cards</td>
        </tr>
        <tr>
            <td colspan="2" class="style6" style=" background-color:#ffcccc">
                Action/Dialog</td>
        </tr>
        <tr>
            <td class="style2" rowspan="3">
                <iframe src="game.jsp" width="825px" height="625px"> scrolling="auto"></iframe>
                </td>
            <td class="style3" style=" background-color: #cccccc">
               Your Card</td>
        </tr>
        <tr>
            <td class="style4" style=" background-color: #ccffcc">
                 Detective Notes</td>
        </tr>
        <tr>
            <td class="style4" style=" background-color:#ffcccc">
                Game Log</td>
        </tr>
    </table>
</body>
</html>