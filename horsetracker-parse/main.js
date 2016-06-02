var Parse = require('parse/node');

Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world, Ian!");
});

