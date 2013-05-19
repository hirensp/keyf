/*
 * JavaScript for the game.html page
 */

/*
 * On first load of the page: adds all the players to the top of the Game,
 *                            adds all the cards of the current client player
 *                            update the first player's actions
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
                .append($('<div style="float: left"/>')
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
                .append($('<div style="float: left"/>')
                    .append($('<img />')
                        .attr('src', jsonCard.image)
                        .attr('height', jsonCard.height)
                        .attr('width', jsonCard.width)));
        });
        // establish the available actions (for the first player)
        // TODO updateState();
    },
    error: function() {
        alert('failure'); // for debugging.
    }}));

// Used by Poll: only States with a new ID are processed
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
        }},
        complete: poll(), // call again on completion
        timeout: 1000 // wait time for response from server
    })},
    2000);  // time between calls to updateState;
});

/*
 * Creates the list of Actions available to the Player
 */
function createActions(data) {
    // clear the previous actions and subActions
    $('#actions').empty();
    $('#subActions').empty();

    if ('actions' in data) {
        $.each(data.actions, function(index, actionString) {
            var action = $.parseJSON(actionString);

            if ('options' in action) {

                $('#actions')
                        .append($('<button style="float: left"/>')
                        .text(action.name));

                createSubActions(action);

                // Hide the actions and show the options for the clicked action.
                $('#actions').click(function() {
                    $('#actions').hide();
                    $('#' + action.name).show();
                });
            }
            else {
                // The action should be caried out as is (no more user input is
                // needed; e.g EndTurn, UnableToRefute)
                $('#actions')
                        .append($('<form  style="float: left"/>')
                        .attr('method', 'POST')
                        .attr('action', action.action)
                        .submit(function(event) {
                    event.preventDefault();
                    $.ajax({
                        url: action.action,
                        type: 'POST',
                        // TODO success: updateState() // update the player's actions
                    });})
                        .append($('<input>')
                        .attr('type', 'submit')
                        .attr('value', action.name)));
            }
        });
    }
};

function createSubActions(action) {
    var actionId = action.name;
    var optionName = action.name + 'Option';

    // create the options available when an action button is clicked.
    var subActionForm = $('<form/>')
            .attr('id', actionId)
            .attr('action', action.action)
            .attr('method', 'POST')
            .submit(function(event) {
        event.preventDefault();

        var data = new Array();
        // accumulate the seleted items
        $('select[name="' + optionName + '"]').each(function(index, item) {
            data[index] = item.options[item.selectedIndex].text;
        });

        $.ajax({
            url: action.action,
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            // TODO success: updateState() // update the available actions
        });})
            .append($('<p style="float: left"/>').text(action.message));

    // create the options (combo boxes)
    $.each(action.options, function(index1, subOptions) {
        var options = $('<select name="' + optionName + '" style="float: left"/>');
        $.each(subOptions, function(index2, option) {
            subActionForm.append(
                    options.append($('<option/>')
                    .attr('value', option)
                    .text(option)));
        });
    });

    // Add the button to the subAction that will actually submit the request
    subActionForm.append($('<input style="float: left"/>')
            .attr('method', 'POST')
            .attr('type', 'submit')
            .attr('value', action.name)
            .click(function() {
        $('#' + actionId).hide();
        $('#actions').show();
    }));

    // Cancel button to return the player to action selection
    subActionForm
            .append($('<button type="button" />')
            .text('cancel')
            .click(function() {
        $('#' + actionId).hide();
        $('#actions').show();
    }));

    // initially, the subAction forms are not visible
    subActionForm.hide();

    $('#subActions').append(subActionForm);
};