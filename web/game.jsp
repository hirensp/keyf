<%-- 
    Document   : game
    Created on : Apr 23, 2013, 11:31:25 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <style type="text/css">
        .main
        {
            width: 800px;
            height: 500px;
        }
        .hallway_sides
        {
            width: 28px;
            height:45px;
        }
        .hallway_hori
        {
            width: 100px;
            height:40px;
            background:#006666;
        }
        .hallway_verti
        {
            width: 30px;
            height:75px;
            background:#006666;
        }
        .room
        {
            width: 150px;
            height:150px;
            border:1px solid black;
            text-align:center;
        }
        
    </style>
</head>
<body>
    <table cellpadding="0" cellspacing="0" class="main">
        <tr>
            <td id="study_hall" colspan="3" rowspan="3" class="room">
                Study</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="hall_room" colspan="3" rowspan="3" class="room">
                Hall</td>
            <td id="miss_scarlet" class="hallway_sides">
                &nbsp;</td>
            <td id="lounge_room" colspan="3" rowspan="3" class="room">
                Lounge</td>
        </tr>
        <tr>
            <td id="study_hall" class="hallway_hori">
                &nbsp;
            </td>
            <td id="hall_lounge" class="hallway_hori">
                &nbsp;</td>
        </tr>
        <tr>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
        </tr>
        <tr>
            <td id="professor_plum" class="hallway_sides">
                &nbsp;</td>
            <td id="study_library" class="hallway_verti">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="hall_billard" class="hallway_verti">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="lounge_dinning" class="hallway_verti">
                &nbsp;</td>
            <td id="col_mustard" class="hallway_sides">
                &nbsp;</td>
        </tr>
        <tr>
            <td id="library_room" colspan="3" rowspan="3" class="room">
                Library</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="billard_room" colspan="3" rowspan="3" class="room">
                Billard Room</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="dinning_room" colspan="3" rowspan="3" class="room">
                Dinning Room</td>
        </tr>
        <tr>
            <td id="library_billard" class="hallway_hori"> 
                &nbsp;</td>
            <td id="billard_dinning" class="hallway_hori">
                &nbsp;</td>
        </tr>
        <tr>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
        </tr>
        <tr>
            <td id="mrs_peacock" class="hallway_sides">
                &nbsp;</td>
            <td id="library_conversatory" class="hallway_verti">
                &nbsp;</td>
            <td class="style15">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="billard_ball" class="hallway_verti">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="dinning_kitchen" class="hallway_verti">
                &nbsp;</td>
            <td class="hallway_sides">
                &nbsp;</td>
        </tr>
        <tr>
            <td id="conversatory_room" colspan="3" rowspan="3" class="room">
                Conversatory</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="ball_room" colspan="3" rowspan="3" class="room">
                Ball Room</td>
            <td class="hallway_sides">
                &nbsp;</td>
            <td id="kitchen_room" colspan="3" rowspan="3" class="room">
                Kitchen</td>
        </tr>
        <tr>
            <td id="conversatory_ball" class="hallway_hori">
                &nbsp;</td>
            <td id="ball_kitchen" class="hallway_hori">
                &nbsp;</td>
        </tr>
        <tr>
            <td id="mr_green" class="hallway_sides">
                &nbsp;</td>
            <td id="mrs_white" class="hallway_sides">
                &nbsp;</td>
        </tr>
    </table>
</body>
</html>
