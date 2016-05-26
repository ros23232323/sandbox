var Parse = require('parse/node');
Parse.initialize('test', 'test');
Parse.serverURL = 'http://localhost:1337/parse';


module.exports = {
    save_obj : function(pObjType,pObj){
        var obj = new Parse.Object(pObjType);
        obj.save(pObj).then(function (obj) {
            console.log(obj.toJSON());
            var query = new Parse.Query('GameScore');
            query.get(obj.id).then(function (objAgain) {
                console.log(objAgain.toJSON());
            }, function (err) {
                console.log(err);
            });
        }, function (err) {
            console.log(err);
        });
    }
}

