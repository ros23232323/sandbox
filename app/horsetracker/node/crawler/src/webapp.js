var config = require('./config/' + (process.env.NODE_ENV || 'test') + '.json');
var express = require('express');
var app = express();
var Parse = require('parse/node');
Parse.initialize(config.parse.appId, config.parse.masterKey);
Parse.serverURL = config.parse.url;

var res_error = {code:"400", msg:""};

app.get('/racecard', function (req, res) {
    var query = new Parse.Query('Racecard');
    query.find({
        success: function(results) {
            res.send(results);
        },

        error: function(error) {
            res_error.msg = error
            res.send(res_error);

        }
    });
})

var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Example app listening at http://%s:%s", host, port)

})
