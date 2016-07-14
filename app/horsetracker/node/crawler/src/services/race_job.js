'use strict';

var Parse = require('parse/node');
var rest_request_utils = require("../utils/rest_request_utils");
var string_utils = require("../utils/string_utils");
var url_builder_utils = require("../utils/url_builder_utils");
var race_parser = require("../parsers/race_parser");
var url_util = require('url');
var _ = require('underscore');

//private vars
var config = null;

//private functions
var create_parse_obj = function(parseObjType,sourceObj){
    var parseObj = new Parse.Object(parseObjType);
    _.each(_.keys(sourceObj),function(key){
        parseObj.set(key, sourceObj[key]);
    });
    return parseObj;
}


module.exports = {
    init: function(mode, cfg){
	console.log('******************************************************');
        console.log('******************************************************');
        console.log('*****App Running in mode "' + mode + '" *****');
        console.log('******************************************************');
        config = cfg;
        console.log('******************************************************');
	Parse.initialize(config.parse.appId, config.parse.masterKey);
	Parse.serverURL = config.parse.url;

    },
    start_job: function(race){
	    var url = config.sporting_life_com.url +  race.get('racecard_url');
        console.log('crawler : ' + config.sporting_life_com.url +  race.get('racecard_url'));

	    var url_parts = url_util.parse(url, true);
        rest_request_utils.get(url,null,function(html) {

            var htmlMD5 = string_utils.hash_string(html);
            var urlMD5 = string_utils.hash_string(url);

            var race_json = race_parser.parse(html, {
                dt: url_parts.path.split('/')[3],
                page_url: url,
                url_hash: urlMD5,
                page_body_hash: htmlMD5
            });

            var race_runners = [];
            _.forEach(race_json.runners,function(runner, runner_index, runner_list){
                var horseParseEntity = create_parse_obj('Entity',runner.horse);
                runner.horse = horseParseEntity;
                var jockeyParseEntity = create_parse_obj('Entity',runner.jockey);
                runner.jockey = jockeyParseEntity;
                var trainerParseEntity = create_parse_obj('Entity',runner.trainer);
                runner.trainer = trainerParseEntity;

                race_runners.push(create_parse_obj('Runner',runner));
            });
            console.log('# runners :: ' + race_runners.length);
            race.set('runners', race_runners);
            Parse.Object.saveAll(race_runners)
            .then(
                function(obj){
                    race.save();
                    console.log('All saved');
                }
            );

            // console.log(race_json);
        });
        //     _.forEach(racecard_json.meetings,function(meeting, meeting_index, meeting_list){
        //         _.forEach(meeting.races,function(race, race_index, race_list){
        //             race.processed = false;
        //             meeting.races[race_index] = create_parse_obj('Race',race);
        //         });
        //         racecard_json.meetings[meeting_index] = create_parse_obj('Meeting', meeting);
        //     });
        //     racecard_json = create_parse_obj('Racecard',racecard_json);
        //
        //     var tmp_list = []
        //     _.forEach(racecard_json.get('meetings'),function(meeting, meeting_index, meeting_list){
        //         tmp_list.push(meeting.get('races'));
        //     });
        //
        //     Parse.Object.saveAll(tmp_list)
        //     .then(
        //         function(obj){
        //             Parse.Object.saveAll(racecard_json.get('meetings'))
        //                 .then(
        //                     function(obj1){
        //                         racecard_json.save(null, {
        //                             success: function(result) {
        //                                 console.log('New object created with objectId: ' + result.id);
        //                             },
        //                             error: function(result, error) {
        //                                 console.log('Failed to create new object, with error code: ' + error.message);
        //                             }
        //                         });
        //                     });
        //             return 'saved';
        //         }
        //     );
        // });
    }
}
