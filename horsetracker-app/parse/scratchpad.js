var config = require('./config/' + (process.env.NODE_ENV || 'test') + '.json');
var Parse = require('parse/node');

Parse.initialize(config.parse.appId, config.parse.masterKey);
Parse.serverURL = config.parse.url;

var Race = Parse.Object.extend("Race");
var query = new Parse.Query(Race);
query.get('lUYfqYl6az',{
  success: function(race) {
    console.log("Successfully retrieved " + race.get('race_name'));
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
