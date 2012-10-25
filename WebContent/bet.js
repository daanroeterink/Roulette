function addToBetList(number)
{
    var currentBets = $("#betLocation").val();
    $("#betLocation").val(currentBets + number + ",");
}


function bet(ammount, location)
{
    var bet = new Object();
    bet['betAmmount'] = 1;
    bet['betLocation'] = 3;

    var jsonString = JSON.stringify(bet);
    $.ajax({
        type: 'POST',
        url: 'api/table/1/player/' + localStorage.getItem('accessToken'),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: jsonString,
        success: function (result)
        {
            alert(result.status);
        },
        error: function (result, ajaxOptions, thrownError)
        {
            alert("Errormessage: " + result.status);
            alert(thrownError);
        }
    });
}