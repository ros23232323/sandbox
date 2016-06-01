APPLICATION_ID=test
MASTER_KEY=test
npm install -g parse-server mongodb-runner parse-dashboard
mongodb-runner start &
sleep 5
parse-server --appId $APPLICATION_ID --masterKey $MASTER_KEY --clientKey $MASTER_KEY --cloud ~/Documents/sandbox/sandbox/local-parse/main.js & 
sleep 5
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://localhost:1337/parse" --appName $APPLICATION_ID &
sleep 5


Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world, Ian!");
});

parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://10.10.2.69:1337/parse" --appName demo &


curl -X POST \
  -H "X-Parse-Application-Id: test" \
  -H "Content-Type: application/json" \
  -d '{}' \
  http://10.10.2.69:1337/parse/functions/hello



curl -X GET \
  -H "X-Parse-Application-Id: test" \
  -H "Content-Type: application/json" \
  http://10.10.2.69:1337/parse/classes/_User

curl -X POST \
  -H "X-Parse-Application-Id: test" \
  -H "Content-Type: application/json" \
  -d '{"username":"john2","password":"john2"}' \
  http://10.10.2.69:1337/parse/classes/_User

#Create user in parse 

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"username":"system","password":"system"}' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/_User


#Create brand post in parse 

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"body":"Check out the new S7!!!!","title":"Samsung S7", "images":["http://www.samsung.com/us/images/common/samsung_logo_seo.jpg"] ,"type":"brand","topics":[{"__type":"Pointer","className":"Topic","objectId":"NBUxduhSXs"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"oypXCrapjf"}}' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/Post

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"body":"Check out the new Apple iFag!!!!","title":"Apple iFag", "images":["http://vignette2.wikia.nocookie.net/freeciv/images/3/3b/Apple_logo.png"],"type":"brand","topics":[{"__type":"Pointer","className":"Topic","objectId":"NBUxduhSXs"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"oypXCrapjf"}}' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/Post


#Create topic in parse 

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"name":"Electronics","type":"category"}' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/Topic



#Create user post in parse 

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"body":"Check out the new Apple iFag!!!!","title":"Apple iFag", "images":["http://vignette2.wikia.nocookie.net/freeciv/images/3/3b/Apple_logo.png"],"type":"brand","topics":[{"__type":"Pointer","className":"Topic","objectId":"NBUxduhSXs"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"L3vxvletv1"}}' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/Post


