function httpGet(func, theUrl, async = true) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, async);
    xmlHttp.onload = func.bind(xmlHttp);
    xmlHttp.send(null);
    return xmlHttp;
}

function getAllFiles(dir) {
    var arrFiles = Array();
    dir.forEach(function(el) {
        if (el.type == 'file') {
            arrFiles.push({ name: el.name, path: el.path });
        } else {
            httpGet(function(e) {
                arrFiles = arrFiles.concat(getAllFiles(JSON.parse(this.responseText)));
            }, 'https://api.github.com/repos/jan75/ch.bfh.bti7081.s2017.yellow/contents/' + el.path + '?ref=develop', false);
        }
    });
    return arrFiles;
}
