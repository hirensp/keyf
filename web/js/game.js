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
            url: 'PollStartGame',
            type: 'GET',
            dataType: 'json',
            success: function(data) {

            },
            error: function(data) {
                alert("poll failed");
            },
            // Poll until we are able to create the game.
            complete: poll,
            timeout: 1000
        });
    }, 2000);
})();