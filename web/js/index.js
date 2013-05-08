$(function() {
    $("#PlayerSelection").submit(function(event) {

      /* stop form from submitting normally */
      event.preventDefault();

      /* Send the data using post and put the results in a div */
        $.ajax({
          url: "GameManagerAddingPlayers",
          type: "post",
          data: { name:    $('input[name=name]').val(),
                  suspect: $('input[name=suspect]:checked').val()},
          success: function() {
              elm = document.getElementById('PlayerSelection');
              elm.parentNode.removeChild(elm);
              $("#waiting").text('waiting on other players...');
          },
          error: function() {
              alert("failure");
          }
        });
    });
});

/*
* Polls for updates.
* If there are enough players to start a game, show the * "start game" button.
* If a Game is already in progress, redirect to the game.
 */
(function poll() {
    setTimeout(function() {
        $.ajax({
            url: 'PollStartGame',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                if (data.canCreateGame) {
                    $("#waiting").hide();
                    $('#startGame').show();
                }

                if (data.gameInProgress) {
                    window.location = 'game.html';
                }
            },
            error: function(data) {
                //alert("poll failed");
            },
            // Poll until we are able to create the game.
            complete: poll,
            timeout: 1000
        });
    }, 2000);
})();
