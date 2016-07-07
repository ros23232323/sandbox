'use strict';

var conf = require('./config/' + (process.env.NODE_ENV || 'test') + '.json');
var job = require('./services/' + (process.env.JOB_NAME || 'racecard')+ '_job');

job.init(process.env.NODE_ENV, conf);
for(var i = -3; i <= 3; i++) {
    job.start_job(i);
}
