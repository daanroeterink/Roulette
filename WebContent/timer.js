var tableRefreshTimer;

$(document).bind("pagebeforechange", function (e, data)
{
    clearInterval(tableRefreshTimer);
    timer();
});

function timer()
{
    tableRefreshTimer = setInterval(function () { getTableInfo(localStorage.getItem('tableID')) }, 1000);
}