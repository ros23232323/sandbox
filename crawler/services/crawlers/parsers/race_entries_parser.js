'use strict';

var cheerio = require('cheerio');

module.exports = {
  parse: function(html){

      var race_entries = []
      var $ = cheerio.load(html);


      $('#racecard tbody tr:not(.disabled)').each(function(i, element){
          var race_entry = $(this);
          race_entries.push({
              running: true,
              stall:race_entry.find('td:nth-child(1)').text().replace(/(\n|\s+)/g,' ').trim(),
              form:race_entry.find('td:nth-child(2)').text().replace(/(\n|\s+)/g,' ').trim(),
              silk_img_link:race_entry.find('td:nth-child(3) img').attr('src'),
              horse:race_entry.find('td:nth-child(3) a').text().replace(/(\n|\s+)/g,' ').trim(),
              profile_url:race_entry.find('td:nth-child(3) a').attr('href')
          });
      });

      //non runners
      $('#racecard tbody tr.disabled').each(function(i, element){
          var race_entry = $(this);
          race_entries.push({
              running: false,
              stall:race_entry.find('td:nth-child(1)').text().replace(/(\n|\s+)/g,' ').trim(),
              form:race_entry.find('td:nth-child(2)').text().replace(/(\n|\s+)/g,' ').trim(),
              silk_img_link:race_entry.find('td:nth-child(3) img').attr('src'),
              horse:race_entry.find('td:nth-child(3) strong').text().replace(/(\n|\s+)/g,' ').trim(),
              profile_url:race_entry.find('td:nth-child(3) a').attr('href')

          });
      });

      return race_entries;
  }
};

