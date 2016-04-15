var url_util = require('url');
var couchbase = require("couchbase");
var crypto = require('crypto');
var fs = require('fs');
var RacecardModel = require("../models/racecard");
var RaceModel = require("../models/race");
var race_crawler = require('../services/race_crawler');
var rest_request_utils = require("../utils/rest_request_utils");
var result_parser = require("../parsers/result_parser");
var string_utils = require("../utils/string_utils");


var hash_tesing = function(){

    var doc1 = fs.readFileSync('../1460314725859.txt');
    var doc2 = fs.readFileSync('../1460314771073.txt');

    var md5sum1 = crypto.createHash('md5');
    var md5sum2 = crypto.createHash('md5');

    md5sum1.update(doc1,'utf8');
    md5sum2.update(doc2,'utf8');

    var digest1 = md5sum1.digest('hex');
    var digest2 = md5sum2.digest('hex');

    if (digest1 === digest2) {
        console.log(digest1);
        console.log(digest2);
    }
}
// hash_tesing();

var url_testing = function(){
    console.log(JSON.stringify(string_utils.url_parse("http://www.sportinglife.com/racing/racecards/14-04-2016/cheltenham/racecard/718172/thoroughbred-breeders-association-mares-novices-hurdle-listed")));
}
 // url_testing();


var couchbase_conn_test = function(){

    var cluster = new couchbase.Cluster('127.0.0.1');
    var bucket = cluster.openBucket('horsetracker-tests', "password", function(err) {
        if (err) {
            // Failed to make a connection to the Couchbase cluster.
            throw err;
        }
    });
    bucket.insert("ian",{name:"Ian Towey"}, function(err,result){
        if (err) {
            throw err;
        }
        console.log(result);
    });
    console.log("doc inserted");

}
// couchbase_conn_test();

var couchbase_racecard_all = function(){

    RacecardModel.find({}, function(error, result) {
        if(error) {
            console.log("An error happened -> " + JSON.stringify(error));
        }
        console.log(JSON.stringify(result));
    });
}
// couchbase_racecard_all ();

var meeting_tester = function(){

    RacecardModel.findByDate('11-04-2016', function(error, result) {
        if(error) {
            console.log("An error happened -> " + JSON.stringify(error));
        }
        var racecardMdl = result[0];
        racecardMdl.cards.forEach(function(meeting, index, array){
            race_crawler.race(meeting);
        });
    });
}
//meeting_tester ();

var result_parse_test = function(){
    var url = "http://www.sportinglife.com/racing/results/13-04-2016/newmarket/result/717970/celebrating-350-years-of-making-history-wood-ditton-stakes-plus-10";
    rest_request_utils.get(url,null,function(html){
        var a = result_parser.parse(html,null);
        console.log(JSON.stringify(a))
    });
}
//result_parse_test();

var couchbase_race_neo4j = function(){

    RaceModel.findByPageUrl("http://www.sportinglife.com/racing/racecards/13-04-2016/penn-national/racecard/718570/claiming", function(error, result) {
        if(error) {
            console.log("An error happened -> " + JSON.stringify(error));
        }
        var race = result[0];
        
        console.log(JSON.stringify(result));
    });
}
couchbase_race_neo4j ();
