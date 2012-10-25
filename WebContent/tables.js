﻿function loadTables()
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

function getTableInfo(id)
{
    $.getJSON('api/table/' + id, function (data)
    {
        localStorage.setItem('tableId', id);

        $('#playerList').empty();
        var bets = data.bets;
        $.each(bets, function (key, bets)
        {
            var username = bets['username'];

            
            }
         
            $('#playerList').append(code);
        });
    });
}