function addMoney()
{
    $.ajax({
        type: 'PUT',
        url: 'api/player/' + localStorage.getItem('accessToken'),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(1000),
        success: function (result)
        {
            alert("1000 coins have been added to your account!");
        },
        error: function (result, ajaxOptions, thrownError)
        {
            alert("Errormessage: " + result.status);
            alert(thrownError);
        }
    });
}