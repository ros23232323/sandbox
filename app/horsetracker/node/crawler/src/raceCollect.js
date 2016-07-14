// 'use strict';

// var conf = require('./config/' + (process.env.NODE_ENV || 'test') + '.json');
// var job = require('./services/' + (process.env.JOB_NAME || 'race')+ '_job');
//
// job.init(process.env.NODE_ENV, conf);
// job.start_job('/racing/racecards/11-07-2016/ruidoso-downs/racecard/733469/trials');


var config = require('./config/' + (process.env.NODE_ENV || 'test') + '.json');
var race_job = require('./services/race_job.js');
var Parse = require('parse/node');

race_job.init('test',config);

var Race = Parse.Object.extend("Race");
var query = new Parse.Query(Race);
query.get('QU9YCLl0Mw',{
  success: function(race) {
    console.log("Successfully retrieved " + race.get('racecard_url'));
    race_job.start_job(race);
  },
  error: function(error) {
    console.log("Error: " + error.code + " " + error.message);
  }
});
