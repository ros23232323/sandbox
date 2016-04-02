
var url = require('url');

module.exports = {
    parse: function(url_str){
        return url.parse(url_str, true);
    }
}