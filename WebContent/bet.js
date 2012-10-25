function addToBetList(number)
{
    $("#betLocation").val(number);
}


function bet(ammount, location)
{
    var bet = new Object();
    bet['betAmmount'] = ammount;
    bet['betLocation'] = parseInt(location);

    var jsonString = JSON.stringify(bet);
    $.ajax({
        type: 'POST',
        url: 'api/table/1/player/' + localStorage.getItem('accessToken'),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: jsonString,
        success: function (result)
        {
            alert("bet");
        },
        error: function (result, ajaxOptions, thrownError)
        {
            alert("Errormessage: " + result.status);
            alert(thrownError);
        }
    });
}