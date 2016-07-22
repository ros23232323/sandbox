'use strict';

var conf = require('./config/' + (process.env.NODE_ENV || 'test') + '.json');
var job = require('./services/' + (process.env.JOB_NAME || 'racecard')+ '_job');

job.init(process.env.NODE_ENV, conf);

for(var i = -3; i <= 3; i++) {
    job.start_job(i);
}

Parse.Cloud.beforeSave(blahClass, function(request, response) {

    var blahUniqueCol = 'uniqueCol';
    var query = new Parse.Query(blahClass);
    query.equalTo(blahUniqueCol, request.object.get(blahUniqueCol));
    query.first({
        success: function(object) {
            if (object) {
                response.error({msg:'A ' + blahClass+ ' with this ' + blahUniqueCol + ' already exists.', obj:object});
            } else {
                response.success();
            }
        },
        error: function(error) {
            response.error('Could not validate uniqueness for this ' + blahClass+ ' object.');
        }
    });
});