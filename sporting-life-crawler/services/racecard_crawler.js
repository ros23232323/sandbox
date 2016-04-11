'use strict';

var crud_service = require('./crud_service');
var meeting_crawler = require('./meeting_crawler');
var rest_request_utils = require("../utils/rest_request_utils");
var string_utils = require("../utils/string_utils");
var racecard_parser = require("../parsers/racecard_parser");
var url_util = require('url');

var RacecardModel = require("../models/racecard");

module.exports = {
    racecard: function(url, options){
        var url_parts = url_util.parse(url, true);
        rest_request_utils.get(url,null,function(html){

            var htmlMD5 = string_utils.hash_string(html);
            
            RacecardModel.findByPageUrl(url, function(error, racecards){
                if(!error){
                    var racecard = racecards[0];
                    if (racecard !== undefined && racecard.page_body_hash === htmlMD5){
                        console.log(url + " body has not changed, doing nothing");
                    } else {

                        //parse doc
                        var racecard_json = racecard_parser.parse(html, {
                            dt:url_parts.path.split('/')[3],
                            page_url:url,
                            page_hash:htmlMD5
                        });

                        //set new docment
                        var racecardNew = new RacecardModel(racecard_json);

                        //save new document
                        crud_service.save(racecardNew);
                        // racecardNew.save(function(error) {
                        //     if(error) {
                        //         console.error(error);
                        //     } else {
                        //         console.log("object saved");
                        //     }
                        // });

                        //remove previous
                        if(racecard !== undefined) {
                            racecard.remove(function (error) {
                                if (error) {
                                    console.log("An error happened -> " + JSON.stringify(error));
                                }
                                console.log("Document was removed!");
                            });
                        }

                        racecardNew.cards.forEach(function(meeting, index, array){
                            meeting_crawler.meeting(meeting);
                        });
                        console.log(url + " body changed, updating");
                    }
                } else {
                    console.log(JSON.stringify(error));
                }
            });
        });
    },

}