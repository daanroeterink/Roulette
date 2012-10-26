﻿function logout()
{
    clearInterval(moneyRefreshTimer);
    timerMoney();
    localStorage.clear();
    $('#loginScreen').html("Username<input type=\"text\" id=\"username\" />Password<input type=\"text\" id=\"password\" /><button id=\"loginButton\">Login</button><button id=\"registerButton\">Register</button>");
    window.location.reload();
}


function login(username, password)
{
    var player = new Object();
    player['username'] = username;
    player['password'] = password;

    var jsonString = JSON.stringify(player);
    $.ajax({
        type: 'POST',
        url: 'api/login',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: jsonString,
        success: function (result)
        {
            localStorage.setItem('Username', result.username);
            localStorage.setItem('Password', result.password);
            localStorage.setItem('accessToken', result.accessToken);
            $('#loginScreen').html("Money:<input id=\"money\"\><br\><button onclick=\"logout();\" id=\"logoutButton\">Logout</button><button onclick=\"addMoney();\">Free Money</button>");
            timerMoney();
            loadTables();
        },
        error: function (result, ajaxOptions, thrownError)
        {
            //alert("Errormessage: " + result.status);
            //alert(thrownError);
        }
    });
}


function downloadBettingTable()
{
    $.get('bord.html', function (data)
    {
        $('#contentDiv').html(data);
    });
    $.get('inzetTafel.html', function (data)
    {
        $('#bettingTable').html(data);
    });
}