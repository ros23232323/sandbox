'use strict';

var conf = require('./config/' + process.env.NODE_ENV + '.json');

var job = require('./services/job');
job.init(process.env.NODE_ENV, conf);
job .start();

