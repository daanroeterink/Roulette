function loadTables()
{
    $("#contentDiv").html("<ul>");

    $.getJSON('api/tables/', function (data)
    {
        var tableObj = data;
        $.each(tableObj, function (key, tableObj)
        {
            var id = tableObj['id'];
            var playerammount = tableObj['players'].length;

            var code = "<li><a onclick=\"joinTable(" + id + ");\" href=\"#\">Table " + id + " Players: " + playerammount + "</a></li>";
            $("#contentDiv").append(code);
        });

        $("#contentDiv").append("<ul>");
    });
}

function joinTable(id)
{
    var player = new Object();
    player['accessToken'] = localStorage.getItem("accessToken");

    var jsonString = JSON.stringify(player);

    $.ajax({
        type: 'POST',
        url: 'api/table/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: jsonString,
        success: function (result)
        {
            localStorage.setItem("tableID", id);
            downloadBettingTable();
            getTableInfo(id);
        },
        error: function (result, ajaxOptions, thrownError)
        {
            alert("Errormessage: " + result.status);
            alert(thrownError);
        }
    });
}

var currentRound = 0;

function getTableInfo(id)
{
    $.getJSON('api/table/' + id, function (data)
    {
        localStorage.setItem('tableId', id);

        $('#playerList').empty();
        var bets = data.bets;
        $.each(bets, function (key, bets)
        {
            var username = bets['player']['username'];

            var betLocation = bets['betLocation'];
            var betAmmount = bets['betAmmount'];

            var code = "<li>" + username + "</li>";
            code += "<ul>";
            code += "<li>on: " + betLocation + "with ammount: " + betAmmount  + "</li>";

            $('#playerList').append(code);
        });

        if (currentRound != data.currentRound)
        {
            for (var i = 0; i < data.lastWinners.length; i++)
            {
                if (data.lastWinners[i] == localStorage.getItem('Username'))
                {
                    alert("You won this time!");
                }
                else
                {
                    alert("You lost. Next time better!");
                }
            }
        }

        currentRound = data.currentRound;
    });
}