var _ = require('underscore');

copy_properties = function(sourceObj, desObj){
    _.each(_.keys(sourceObj),function(key){
        desObj.set(key, sourceObj[key]);
    });
}


var Parse = require('parse/node');
Parse.initialize('test', 'test');
Parse.serverURL = 'http://localhost:1337/parse';


module.exports = {
    save_obj : function(pObjType,pObj){
        var obj = new Parse.Object(pObjType);
        copy_properties(pObj, obj);
        obj.save().then(function (obj) {
            console.log(obj.toJSON());
            var query = new Parse.Query('Racecard');
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

