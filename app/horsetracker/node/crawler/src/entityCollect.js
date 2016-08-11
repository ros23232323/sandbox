// 'use strict';

var config = require('./config/test.json');
var entity_job = require('./services/entity_job.js');
var Parse = require('parse/node');

entity_job.init('test',config);

var Entity = Parse.Object.extend("Entity");
var query = new Parse.Query(Entity);
var entityId = process.argv[2];
if(entityId){
	query.get(entityId,{
	  success: function(entity) {
	    console.log("Successfully retrieved " + entity.get('profile_url'));
	    entity_job.start_job(entity);
	  },
	  error: function(error) {
	    console.log("Error: " + error.code + " " + error.message);
	  }
	});
}
