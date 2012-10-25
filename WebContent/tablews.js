var WNumber = {};

WNumber.socket = null;

WNumber.connect = (function (host)
{
    WNumber.socket = new WebSocket(host);


    WNumber.socket.onopen = function ()
    {

    };

    WNumber.socket.onclose = function ()
    {

    };

    WNumber.socket.onmessage = function (message)
    {
        alert(message.data);
        var n = message.data.split();
        if (n[0] == localStorage.getitem('tableID'))
        {
            spin(n[1]);
        }


    };
});

WNumber.initialize = function ()
{
    if (window.location.protocol == 'http:')
    {
        WNumber.connect('ws://' + window.location.host + '/Roulette/bordnumber');
    } else
    {
        WNumber.connect('wss://' + window.location.host + '/Roulette/bordnumber');
    }
};

WNumber.sendMessage = (function ()
{
    Chat.socket.send("Test");
});

WNumber.initialize();