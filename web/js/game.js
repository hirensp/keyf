/*
 * JavaScript for the game.html page
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