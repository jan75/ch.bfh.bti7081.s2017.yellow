var Helper = function() {
    // define constants 

    var tok1 = '71c674db';
    var tok3 =  '56bb88cfce7d4115';
    var tok2 = 'a8f7b29d9f46bae7';
    const gh = new GitHub({
        username: 'samsamann',
        token: tok1+tok2+tok3
    });
    const repo = gh.getRepo('jan75', 'ch.bfh.bti7081.s2017.yellow');

    var getRepoContent = function(path, branch = 'develop') {
        return repo.getContents(branch, path, false);
    }

    var getRawContent = function(path, branch = 'develop') {
        return repo.getContents(branch, path, true);
    }

    var getAllFiles = function(dir, branch = 'develop') {
        var promise = new Promise(function(resolve, reject){
            getRepoContent(dir, branch).then(function({data}) {
                var arrFiles = Array();
                data.forEach(function(el) {
                    if (el.type == 'file') {
                        arrFiles.push({ name: el.name, path: el.path, html: el.html_url });
                    }
                });
                resolve(arrFiles);
            });
        });
        
        return promise;
    }

    return {
        getAllFiles: getAllFiles,
        getRawContent: getRawContent
    };
};
