'use strict';

module.exports = {
    scrub_whitespace: function(str) {
        return str.replace(/(\s+|\n)/g,' ').trim();
    },
    scrub_obj_whitespace: function(obj) {
        return JSON.stringify(obj).replace(/(\s+|\n)/g,' ').trim();
    },
    scrub_json_arr_obj_strs:function(arr){
        for(var idx in arr){
            this.scrub_json_obj_strs(arr[idx]);
        }
    },
    scrub_json_obj_strs:function(obj){
        if ((typeof obj) === 'string') {
            obj = this.scrub_whitespace(obj[key]);
        } else {
            for (var key in obj) {
                if (Array.isArray(obj[key])) {
                    this.scrub_json_arr_obj_strs(obj[key]);
                } else if ((typeof obj[key]) === 'object') {
                    this.scrub_json_obj_strs(obj[key]);
                }
            }
        }
    },
    nvl: function(s1){
        if (s1 === null ){
            return "";
        } else {
            return s1;
        }
    }

};