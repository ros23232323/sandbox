var Parse = require('parse/node');

Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world, Ian!");
});

var blahClass = 'Blah';

Parse.Cloud.beforeSave(blahClass, function(request, response) {

    var blahUniqueCol = 'uniqueCol';
    var query = new Parse.Query(blahClass);
    query.equalTo(blahUniqueCol, request.object.get(blahUniqueCol));
    query.first({
        success: function(object) {
            if (object) {
                response.error({msg:'A ' + blahClass+ ' with this ' + blahUniqueCol + ' already exists.', obj:object});
            } else {
                response.success();
            }
        },
        error: function(error) {
            response.error('Could not validate uniqueness for this ' + blahClass+ ' object.');
        }
    });
});

/*Parse.initialize('9c188b00235ac01ec4bff34fcd3f7838', '9c188b00235ac01ec4bff34fcd3f7838');
Parse.serverURL = 'http://192.168.1.102:1337/parse';

  query.find(null, {
    success: function(post) {
      post.increment("comments");
      post.save();
    },
    error: function(error) {
      console.error("Got an error " + error.code + " : " + error.message);
    }
  });

query.find({
  success: function(results) {
    var race = results[0]
    console.log("Successfully retrieved " + results.length + " scores.");
  },
  error: function(error) {
    console.log("Error: " + error.code + " " + error.message);
  }
});


  if (request.object.get("stars") < 1) {
    response.error("you cannot give less than one star");
  } else if (request.object.get("stars") > 5) {
    response.error("you cannot give more than five stars");
  } else {
    response.success();
  }
});


Parse.Cloud.afterSave("Blah", function(request) {
  query = new Parse.Query("Post");
  query.get(request.object.get("post").id, {
    success: function(post) {
      post.increment("comments");
      post.save();
    },
    error: function(error) {
      console.error("Got an error " + error.code + " : " + error.message);
    }
  });
});

*/
