'use strict';

var Parse = require('parse/node');
var rest_request_utils = require("../utils/rest_request_utils");
var string_utils = require("../utils/string_utils");
var url_builder_utils = require("../utils/url_builder_utils");
var entity_details_parser = require("../parsers/entity_details_parser");
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
    start_job: function(entity){

      var url = config.sporting_life_com.url + entity.get('profile_url');

      console.log('crawl : ' + url);

      var url_parts = url_util.parse(url, true);
      rest_request_utils.get(url,null,function(html) {

          var htmlMD5 = string_utils.hash_string(html);
          var urlMD5 = string_utils.hash_string(url);

          var entity_json = entity_details_parser.parse(html, {
              dt: url_parts.path.split('/')[3],
              page_url: url,
              profile_url:entity.get('profile_url'),
              url_hash: urlMD5,
              page_body_hash: htmlMD5
          });

          console.log(JSON.stringify(entity_json));
          entity.set('ed_collect_date',new Date());
          entity.set('entity_details',entity_json);
          entity.save();
      });
    }
}
