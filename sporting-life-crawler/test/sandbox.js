var url_util = require('url');
var couchbase = require("couchbase");
var crypto = require('crypto');
var fs = require('fs');
var RacecardModel = require("../models/racecard");

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
    console.log(JSON.stringify(url_util.parse(" http://www.sportinglife.com/racing/racecards/09-04-2016", true)));
}
// url_testing();


var couchbase_conn_test = function(){

    var cluster = new couchbase.Cluster('127.0.0.1');
    var bucket = cluster.openBucket('horsetracker-test', "password", function(err) {
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
couchbase_racecard_all ();