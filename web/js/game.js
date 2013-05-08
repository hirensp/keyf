/*
 * JavaScript for the game.html page
 */

/*
 * On first load of the page: adds all the players to the top of the Game,
 *                            adds all the cards of the current client player
 */
$(document).ready($.ajax({
<<<<<<< HEAD
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
                //alert("poll failed");
            },
            // Poll until we are able to create the game.
            complete: poll,
            timeout: 1000
=======
    url: 'InitializeGame',
    type: 'POST',
    dataType: 'json',
    success: function(data) {
        $.each(data.players, function(index, player) {
            var jsonPlayer = $.parseJSON(player);

            var newDiv = $('<div style="float: left"></div>');

            newDiv.append($('<img />').attr(
                    {src: jsonPlayer.image,
                     height: jsonPlayer.height,
                     width: jsonPlayer.width}));

            newDiv.append($('<p />', {text: jsonPlayer.name}));

            $("#players").append(newDiv);
        });

        $.each(data.cards, function(index, card) {
            $("#cards")
                .append($('<div></div>')/*.css("float: left") */
                    .append($('<img />').attr('src' ,card.image)
                                        .attr('height', card.height)
                                        .attr('width', card.width)));
>>>>>>> parent of f6ba02d... Cards now properly populate on intiailization of the game.html
        });
    }, 2000);
})();
