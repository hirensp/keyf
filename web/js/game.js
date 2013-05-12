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
       // Initialize players at the top of the screen
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
       // Initialize cards
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
       alert('failure'); // for debugging.
   }}));

// Used by the poll: only States with a new ID are processed
var lastStateId = 'not an id';

/*
 * Polls for updates to the game
 */
$(document).ready(function poll() {
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
                    createActions(data);
                }
            },
            error: function(data) {
                // TODO is this failing for a good reason?
                // alert("poll failed"); // for debugging
            },
            // call this function again on completion
            complete: poll,
            // wait time for response to server
            timeout: 1000});
    },
    2000); // time between calls to this function);
});

/*
 * Creates the line of Actions available to the Player
 */
function createActions(data) {
    // clear the previous actions and subActions
    $('#actions').empty();
    $('#subActions').empty();

    $.each(data.actions, function(index, actionString) {
        var action = $.parseJSON(actionString);

        var actionId = action.name;

        $('#actions').append($('<button style="float: left"/>')
                .text(action.name)
                .click(function(event) {
                    $('#actions').hide();
                    $('#' + actionId).show();
        }));

        // create the options available when an action button is clicked.
        var subActionForm = $('<form/>')
                .attr('id', actionId)
                .attr('action', action.action)
                .attr('method', 'POST')
                .append($('<p style="float: left"/>').text(action.message));

        var optionName = action.name + 'option';

        // create the options (Dropdowns) if they're present
        if ('options' in action) {
            $.each(action.options, function(index1, subOptions) {
                var options = $('<select name="' + optionName + '" style="float: left"/>');
                $.each(subOptions, function(index2, option) {
                    subActionForm.append(
                            options.append($('<option/>')
                            .attr('value', option)
                            .text(option)));
                });
            });
        }

        // Add the button to the subAction that will actually submit the request
        subActionForm.append($('<input style="float: left"/>')
                .attr('method', 'POST')
                .attr('type', 'submit')
                .attr('value', action.name)
                .submit(function() {
            $.ajax({
                url: action.action,
                type: 'POST',
                data: function() {
                    var data = new Array();
                    // accumulate the seleted items
                    $('select[name="' + optionName + '"] select:selected').each(
                            function(index) { data[index] = $(this).text(); });
                    return JSON.stringify(data);

                },
                dataType: 'json',
                success: function(data) {
                    alert(JSON.stringify(data));
                }});
        }));

        // Cancel button to return the player to action selection
        subActionForm.append($('<button type="button" />').text('cancel').click(function() {
            $('#' + actionId).hide();
            $('#actions').show();
        }));

        // initially, the subAction forms are not visible
        subActionForm.hide();

        $('#subActions').append(subActionForm);
    });
};