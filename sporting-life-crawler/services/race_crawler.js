/**
 * Created by ian on 10/04/16.
 */

var conf = require('../config/' + process.env.NODE_ENV + '.json');

var crud_service = require('./crud_service');
var rest_request_utils = require("../utils/rest_request_utils");
var race_parser = require('../parsers/race_parser');
var string_utils = require('../utils/string_utils');
var url_util = require('url');

var _ = require('underscore');
var RaceModel = require('../models/race');

var get_runners = function(url){

    var url_parts = url_util.parse(url, true);
    var raceModel = new RaceModel({});
    rest_request_utils.get(url,null,function(html){

        var htmlMD5 = string_utils.hash_string(html);
        RaceModel.findByPageUrl(url, function(error, races){
            if(!error){
                var race = races[0];
                if (race !== undefined && race.page_body_hash === htmlMD5){
                    console.log(url + " body has not changed, doing nothing");
                } else {
                    //parse doc
                    var race_json = race_parser.parse(html, {
                        dt:url_parts.path.split('/')[3],
                        page_url:url,
                        page_hash:htmlMD5
                    });

                    //set new docment
                    var raceNew = new RaceModel(race_json);

                    //save new document
                    crud_service.save(raceNew);
                }
            } else {
                
            }
        });
    });
}

var get_result = function(url){
    console.log('\t\t\tTODO:: parse result ' + url);
    rest_request_utils.get(url,null,function(html){

    });
}
module.exports = {
    race:function(meeting){

        console.log('TODO:: parse races ' + meeting.track);
        _.filter(meeting.races, function(ele){
            return ele.abandoned !== 'ABD';
        })
        .forEach(function(raceElement, index, array){
            (function() {
                setTimeout(function () {
                    get_runners(conf.sporting_life_com.url + raceElement.racecard_url)
                }, Math.floor(index + Math.random() * conf.sporting_life_com.max_timeout));
            })();
            // if (raceElement.result_url !== undefined) {
            //     (function () {
            //         setTimeout(function () {
            //             get_result(raceElement.result_url)
            //         }, Math.floor(Math.random() * 15000));
            //     })();
            // }
        });

    }
}