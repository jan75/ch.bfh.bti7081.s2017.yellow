var Helper = function() {
    // define constants 
    const gh = new GitHub({
        username: 'samsamann',
        token: 'ab41d1b6c0177573260472084564a568e15542c3'
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
