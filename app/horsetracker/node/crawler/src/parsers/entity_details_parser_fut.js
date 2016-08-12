'use strict';

var string_utils = require('../utils/string_utils');
var cheerio = require('cheerio');

module.exports = {
    parse:function(html, opts){
        var horse_details = {};

        var $ = cheerio.load(html);

        var racecard_header = $('.racecard-header.t3 .content-header');

        horse_details = {
            name : racecard_header.find('h2').text(),
            age : racecard_header.find('ul li:nth-child(1)').text(),
            profile_url: opts.profile_url,
            collection_date: opts.collection_date,
            sex :  racecard_header.find('ul li:nth-child(2)').text(),
            sire : {
                name:racecard_header.find('ul li:nth-child(3) a').text(),
                profile_url:racecard_header.find('ul li:nth-child(3) a').attr('href')
            },
            dam :  {
                name:racecard_header.find('ul li:nth-child(4) a').text(),
                profile_url:racecard_header.find('ul li:nth-child(4) a').attr('href')
            },
            trainer :  {
                name:racecard_header.find('ul li:nth-child(6) a:nth-child(2)').text(),
                profile_url:racecard_header.find('ul li:nth-child(6) a:nth-child(2)').attr('href')
            },
            owner :  racecard_header.find('ul li:nth-child(7)').text(),
            fut_ent : []
        };

        $('.tbl.t3 tbody tr').each(function(i, element){
            var fut_ent = $(this);
            horse_details.fut_ent.push({
                time_date:fut_ent.find('td:nth-child(1) a').text(),
                meeting_url:fut_ent.find('td:nth-child(1) a').attr('href'),
                track_name:fut_ent.find('td:nth-child(2)').text(),
                distance:fut_ent.find('td:nth-child(3)').text(),
                class:fut_ent.find('td:nth-child(4)').text(),
                jockey:{
                    name:fut_ent.find('td:nth-child(5) a').text(),
                    profile_url:fut_ent.find('td:nth-child(5) a').attr('href')
                },
                trainer:{
                    name:fut_ent.find('td:nth-child(6) a').text(),
                    profile_url:fut_ent.find('td:nth-child(6) a').attr('href')
                },
                odds:fut_ent.find('td:nth-child(7) a').text()
            }) ;
        });

      return horse_details;
    }
};
