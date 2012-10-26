var tableRefreshTimer;
var moneyRefreshTimer;

$(document).bind("pagebeforechange", function (e, data)
{
    clearInterval(tableRefreshTimer);
    timer();
});

function timer()
{
    tableRefreshTimer = setInterval(function () { getTableInfo(localStorage.getItem('tableID')) }, 1000);
}

function timerMoney()
{
    tableRefreshTimer = setInterval(function () { getMoney() }, 5000);
}