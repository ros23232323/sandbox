var url_parse = require("../js/url_parse");

describe("Parse url", function() {

    before(function(done){
        setTimeout(function(){
            foo = true;
            done();
        }, 1000);
    });

    it("parse url", function (done) {

        var url = "http://www.sportinglife.com/ajax/racing/profile-search?search=star&type=H&page=1";
        var url_parts = url_parse.parse(url);

        console.log(JSON.stringify(url_parts));
        done();
    });
});