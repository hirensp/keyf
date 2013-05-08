/*
 * JavaScript for the game.html page
 */

/*
 * On first load of the page: adds all the players to the top of the Game,
 *                            adds all the cards of the current client player
 */
$(document).ready($.ajax({
   url: 'InitializeGame',
   type: 'POST',
   dataType: 'json',
   success: function(data) {
       $.each(data.players, function(index, player) {
           var jsonPlayer = $.parseJSON(player);
           $("#players")
               .append($('<div/>')
                   .css("float", "left")
                   .append($('<img />')
                       .attr('src', jsonPlayer.image)
                       .attr('height', jsonPlayer.height)
                       .attr('width', jsonPlayer.width))
                   .append($('<p />').text(jsonPlayer.name)));
       });

       $.each(data.cards, function(index, card) {
           var jsonCard = $.parseJSON(card);
           $("#cards")
               .append($('<div/>')
                   .css("float", "left")
                   .append($('<img />')
                       .attr('src' ,jsonCard.image)
                       .attr('height', jsonCard.height)
                       .attr('width', jsonCard.width)));
       });
   },
   error: function() {
       alert('failure');
   }}));

/*
 * Polls for updates to the game
 */
(function poll() {
    setTimeout(function() {
        $.ajax({
            url: 'Poll',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                $('#suspectMessage').text(data.suspectMessage);
                $('#log').append($('<p />').text(data.logMessage));

                for (var actionString in data.actions) {
                    var action = $.parseJSON(actionString);

                    $('<input/>').attr("type", "button").attr("value", action.name);
                    /* todo figure out how to setup sub action stuff */
                }
                $('#actions').html(/* complicated crap */);

                /* more crap about which characters and weapons and stuff moved where! */
            },
            error: function(data) {
                //alert("poll failed");
            },
            complete: poll,
            timeout: 1000
        });
    }, 2000);
})();
