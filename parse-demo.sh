APPLICATION_ID=test
MASTER_KEY=test
npm install -g parse-server mongodb-runner parse-dashboard
mongodb-runner start &
sleep 5
parse-server --appId $APPLICATION_ID --masterKey $MASTER_KEY --cloud /tmp/tmp-app/main.js & 
sleep 5
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://localhost:1337/parse" --appName $APPLICATION_ID &
sleep 5


Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world, Ian!");
});

parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://localhost:23740/parse" --appName demo &


curl -X POST \
  -H "X-Parse-Application-Id: test" \
  -H "Content-Type: application/json" \
  -d '{}' \
  http://10.10.2.69:1337/parse/functions/hello
