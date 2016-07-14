var config = require('./src/config/' + (process.env.NODE_ENV || 'test') + '.json');
var race_job = require('./src/services/race_job.js');
var Parse = require('parse/node');

race_job.init('test',config);

var Race = Parse.Object.extend("Race");
var query = new Parse.Query(Race);
query.get('QU9YCLl0Mw',{
  success: function(race) {
    console.log("Successfully retrieved " + race.get('racecard_url'));
    race_job.start_job(race.get('racecard_url'));
  },
  error: function(error) {
    console.log("Error: " + error.code + " " + error.message);
  }
});

query.find({
  success: function(results) {
    var race = results[0]
    console.log("Successfully retrieved " + results.length + " scores.");
  },
  error: function(error) {
    console.log("Error: " + error.code + " " + error.message);
  }
});
