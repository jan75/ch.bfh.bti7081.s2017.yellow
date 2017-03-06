function httpGet(func, theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, true);
    xmlHttp.onload = func.bind(xmlHttp);
    xmlHttp.send(null);
    return xmlHttp;
}
