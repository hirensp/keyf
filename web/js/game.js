/*
 * JavaScript for the game.html page
 */
$(document).ready($.ajax({
    url: 'InitializeGame',
    type: 'post',
    dataType: 'json',
    success: function(data) {
        $.each(data.players, function(index, player) {
            alert(JSON.stringify(player));
            var playerSplash = document.getElementById('players');
            playerSplash.appendChild($('<div/>').html(player).contents());
        });

        $.each(data.cards, function(index, card) {
            var playerCards = document.getElementById('cards');
            playerCards.appendChild($('<div/>').html(card).contents());
        });
    },
    error: function() {
        alert('failure');
    }}));