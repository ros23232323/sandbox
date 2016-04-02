'use strict';

var cheerio = require('cheerio');

module.exports = {

  parse: function(html, options){

      var race_result = []
      var $ = cheerio.load(html);


      $('.racecard-header .content-header h2').html().replace(/<span.*<\/span>/g,'') //racename
      $('.racecard-header .content-header ul li:nth-child(1)').text() //racae classification
      $('.racecard-header .content-header ul li:nth-child(2)').text() //prise money
      $('.racecard-header .content-header ul li:nth-child(3)').text() // going
      $('.racecard-header .content-header ul li:nth-child(4)').text() // surface

      $('#racecard-tabs .tbl.t3 tbody tr:nth-child(1) td:nth-child(3)').text() //row detail


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

      return race_result;
  }
};

