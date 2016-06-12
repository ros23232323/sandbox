'use strict';

var conf = require('./config/' + (process.env.NODE_ENV || 'test') + '.json');
var job = require('./services/' + (process.env.JOB_NAME || 'racecard')+ '_job');

job.init(process.env.NODE_ENV, conf);
for(var i = -2; i <= 2; i++) {
    job.start_job(i);
}


