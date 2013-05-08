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

/* Used by the poll */
var lastStateId = 'not an id';

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
                if (lastStateId !== data.id) {
                    lastStateId = data.id;
                    $('#suspectMessage').text(data.suspectMessage);
                    $('#log').append($('<p />').text(data.logMessage));

                    $('#actions').empty(); // clear the previous actions
                    $('#subActions').empty();
                    $.each(data.actions, function(index, actionString) {
                        var action = $.parseJSON(actionString);

                        $('#actions').append($('<button style="float: left"/>')
                                .text(action.name)
                                .click(function(event) {
                                    $('#actions').hide();
                                    $(document.getElementById(action.name)).show();
                                }));

                        /* todo figure out how to setup sub action stuff */
                        var subActionForm = $('<form/>')
                            .attr('id', action.name)
                            .attr('action', action.action)
                            .attr('method', 'GET')
                            .append($('<p style="float: left"/>').text(action.message));

                        if ('options' in data) {
                            $.each(data.options, function(index1, subOptions) {
                                $.each(subOptions, function(index2, option) {
                                    $('<select style="float: left"/>')
                                        .append($('<option/>').text(option));
                                });
                            });
                        }

                        subActionForm.append($('<input style="float: left"/>')
                            .attr('type', 'submit')
                            .attr('value', action.name));

                        subActionForm.append($('<button>').text('cancle'));

                        subActionForm.hide();

                        $('#subActions').append(subActionForm);
                    });
                }
            },
            error: function(data) {
                //alert("poll failed");
            },
            complete: poll,
            timeout: 1000
        });
    }, 2000);
})();
