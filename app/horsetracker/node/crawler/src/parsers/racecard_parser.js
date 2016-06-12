'use strict';

var cheerio = require('cheerio');
var string_utils = require('../utils/string_utils')
var date_utils = require('../utils/date_utils')

module.exports = {
    parse: function(html,options){
        var $ = cheerio.load(html);

        var daily_racecard = {
            createdON:new Date(),
            date:date_utils.parseDate(options.dt),
            page_url:options.page_url,
            page_url_hash:options.url_hash,
            page_body_hash:options.page_hash,
            meetings:[]
        };

        $('.r .rac-dgp').each(function(i, element){
            var race_card_section = $(this);
            var meeting = {
                track:race_card_section.find('.hdr.t2 a').text().trim(),
                track_url:race_card_section.find('.hdr.t2 a').attr('href'),
                track_going:race_card_section.find('.list.hdr-sublinks li:nth-child(1):has(strong)').html().replace(/<strong>.*<\/strong>/g,''),
                track_surface:string_utils.nvl(race_card_section.find('.list.hdr-sublinks li:nth-child(2):has(strong)').html()).replace(/<strong>.*<\/strong>/g,''),
                races:[]
            };
            var tmp_races = [];
            race_card_section.find('.rac-cards').each(function(i, element){
                var race_section = $(this);
                var tmp_race = {
                    race_time:race_section.find('.ix.ixt').text(),
                    race_name:race_section.find('.ix.ixc').text().replace(/\n/g,' ').replace(/\s+/g,' '),
                    racecard_url:race_section.find('.ix.ixc a').attr('href'),
                    result_url:race_section.find('.ix.ixv a').attr('href'),
                    abandoned:race_section.find('.ix.ixv .dnf').text()
                };
                tmp_races.push(tmp_race);
            });
            meeting.races = tmp_races;
            daily_racecard.meetings.push(meeting);
        });

        return daily_racecard;
    }
}


