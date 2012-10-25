function register()
{
    var player = new Object();
    var username = $('#regusername').val();
    var password = $('#regpassword').val();

    player['username'] = username;
    player['password'] = password;

    var jsonString = JSON.stringify(player);
    $.ajax({
        type: 'POST',
        url: 'api/players',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: jsonString,
        success: function (result)
        {
            login(username, password);
        },
        error: function (result, ajaxOptions, thrownError)
        {
            alert("Errormessage: " + result.status);
            alert(thrownError);
        }
    });
}