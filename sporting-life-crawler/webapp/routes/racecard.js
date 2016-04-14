var express = require('express');
var router = express.Router();
var RacecardModel = require("../../models/racecard");

/* GET racecard list . */
router.get('/', function(req, res, next) {
  RacecardModel.find({}, function(error, results) {
    if(error) {
      console.log("An error happened -> " + JSON.stringify(error));
    } else {
      res.send(JSON.stringify(results));
    }
  });
});

router.get('/:dt', function(req, res, next) {
  RacecardModel.find({}, function(error, results) {
    if(error) {
      console.log("An error happened -> " + JSON.stringify(error));
    } else {
      res.send(JSON.stringify(results));
    }
  });
});

module.exports = router;
