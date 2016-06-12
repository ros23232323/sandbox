'use strict';
var dateFormat = require('dateformat');

module.exports = {
    formatted_date: function(dt, format) {
        return dateFormat(dt, format);
    },
    parseDate : function (input) {
        //dd-mm-yyyy
        var parts = input.split('-');
        return new Date(parts[2], parts[1]-1, parts[0]); // Note: months are 0-based
    }
};