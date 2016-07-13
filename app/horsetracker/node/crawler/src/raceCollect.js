'use strict';

var conf = require('./config/' + (process.env.NODE_ENV || 'test') + '.json');
var job = require('./services/' + (process.env.JOB_NAME || 'race')+ '_job');

job.init(process.env.NODE_ENV, conf);
job.start_job('/racing/racecards/11-07-2016/ruidoso-downs/racecard/733469/trials');
