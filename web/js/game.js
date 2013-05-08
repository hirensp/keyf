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
        });
    },
    error: function() {
        alert('failure');
    }}));