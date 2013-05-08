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

(function poll() {
    setTimeout(function() {
        $.ajax({
            url: 'PollStartGame',
            type: 'GET',
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
            dataType: 'json',
            // Poll until we are able to create the game.
            complete: poll,
            timeout: 1000
        });
    }, 2000);
})();
