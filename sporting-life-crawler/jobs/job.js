'use strict';

var crontab = require('node-crontab');
var racecard_crawler = require('./../services/racecard_crawler');

var rest_request_utils = require("./../utils/rest_request_utils");
var url_builder_utils = require("./../utils/url_builder_utils");
var config = null;
var is_running = false;

module.exports = {
    init: function(mode, cfg){

        console.log('******************************************************');
        console.log('******************************************************');
        console.log('*****App Running in mode "' + mode + '"*****');
        console.log('******************************************************');
        config = cfg;
        console.log('******************************************************');
    },
    start: function(){
        var dt = new Date();
        // var jobId = crontab.scheduleJob(config.cron.racecard, function(){ //This will call this function every 2 minutes
        //     if(!is_running) {
                is_running = true;
                console.log("crawling url..... " + url_builder_utils.sp_raceards_url(config, dt) );
                racecard_crawler.racecard(url_builder_utils.sp_raceards_url(config, dt));
                is_running = false;
            // } else {
            //     console.log("job in progress");
            // }
        // });
        // console.log( jobId + " :: job started on " + new Date().toString());
    }
}