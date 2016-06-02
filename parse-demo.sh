PARSE_APP_HOST=localhost
PARSE_APP_PORT=1337
CLOUD_DIR=/home/ian/Documents/sandbox/horsetracker-parse
APPLICATION_ID=test
MASTER_KEY=test
export NODE_PATH=/usr/local/lib/node_modules
sudo npm install -g parse-server mongodb-runner parse-dashboard parse
mongodb-runner start &
sleep 5
parse-server --appId $APPLICATION_ID --masterKey $MASTER_KEY --cloud $CLOUD_DIR/main.js --port $PARSE_APP_PORT & 
sleep 5
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://$PARSE_APP_HOST:$PARSE_APP_PORT/parse" --appName $APPLICATION_ID &

APPLICATION_ID=DRIP
MASTER_KEY=DRIP
npm install -g parse-server mongodb-runner parse-dashboard
mongodb-runner start &
sleep 5
parse-server --appId $APPLICATION_ID --masterKey $MASTER_KEY --clientKey $MASTER_KEY --cloud ~/Documents/sandbox/sandbox/local-parse/main.js & 
sleep 5
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://localhost:23740/parse" --appName $APPLICATION_ID &
sleep 5

parse-dashboard --appId DRIP --masterKey DEVMASTERKEY --serverURL "http://10.10.2.69:23740/parse" --appName DRIP &



Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world, Ian!");
});

parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://10.10.2.69:1337/parse" --appName demo &




PARSE_HOST=nodeweb01.androidapp.dev.ostk.com
PARSE_HOST=localhost
PARSE_PORT=23743
PARSE_APP=DRIP
PARSE_MASTER_KEY=DEVMASTERKEY
#call cloud function
PARSE_HOST=nodeweb01.iosoapp.dev.ostk.com
PARSE_PORT=23740
curl -X POST \
  -H "X-Parse-Application-Id: $PARSE_APP" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/functions/hello

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{}' \
  http://$PARSE_APP_HOST:$PARSE_APP_PORT/parse/functions/hello
  "http://$PARSE_HOST:$PARSE_PORT/parse/functions/timeline?userId=m6RLkdTsAK&limit=100&skip=0"

curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  -d '{"userId":"6vlpznuyEz","limit":100,"skip":0}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/functions/timeline


curl -X GET \
  -H "X-Parse-Application-Id: test" \
  -H "Content-Type: application/json" \
  http://10.10.2.69:1337/parse/classes/_User

curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"username":"js","password":"js","email":"js@o.com"}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/_User

curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  -d '{"username":"system","password":"system"}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/_User

#Create user in parse 

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"username":"system","password":"system"}' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/_User

PARSE_HOST=nodeweb01.iosoapp.dev.ostk.com
PARSE_PORT=23740
curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"name":"js","tagline":"anonymous ..."}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/UserProfile



#Create brand post in parse 

PARSE_MASTER_KEY=DEVMASTERKEY

curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"body":"Check out the new S7!!!!","title":"Samsung S7", "images":[] ,"type":"user","topics":[{"__type":"Pointer","className":"Topic","objectId":"RlwbXWHEnX"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"TQaBshmJg8"}}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Post

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"body":"Check out the new Apple iFag!!!!","title":"Apple iFag", "images":["http://vignette2.wikia.nocookie.net/freeciv/images/3/3b/Apple_logo.png"],"type":"brand","topics":[{"__type":"Pointer","className":"Topic","objectId":"82RPtkE6aF"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"LwGyWLEJ2E"}}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Post

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"body":"Blah blah blah ....","title":"Checkout my post", "images":["http://vignette2.wikia.nocookie.net/freeciv/images/3/3b/Apple_logo.png"],"type":"user","topics":[{"__type":"Pointer","className":"Topic","objectId":"82RPtkE6aF"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"6vlpznuyEz"}}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Post

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"body":"Blah blah blah ....","title":"Checkout my post 22", "images":["http://vignette2.wikia.nocookie.net/freeciv/images/3/3b/Apple_logo.png"],"type":"user","topics":[{"__type":"Pointer","className":"Topic","objectId":"MkN7FV9xM3"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"VDjgwRpbZK"}}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Post


#Create topic in parse 

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"name":"Electronics","type":"category"}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Topic



#Create user post in parse 

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"body":"Check out the new Apple iFag!!!!","title":"Apple iFag", "images":["http://vignette2.wikia.nocookie.net/freeciv/images/3/3b/Apple_logo.png"],"type":"brand","topics":[{"__type":"Pointer","className":"Topic","objectId":"NBUxduhSXs"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"L3vxvletv1"}}' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/Post


#Create userFloows in parse 

function addUserFollow(){

user=$1
follows=$2

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"user":{"__type":"Pointer","className":"_User","objectId":"${user}"},"follows":{"__type":"Pointer","className":"_User","objectId":"${follows}"} }' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/UserFollows

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"user":{"__type":"Pointer","className":"_User","objectId":"${follows}"},"followedBy":{"__type":"Pointer","className":"_User","objectId":"${user}"} }' \
  http://nodeweb01.androidapp.dev.ostk.com:23741/parse/classes/UserFollowedBy

}


addUserFollow vwmKdgXv20 iFU8Isbq7F



curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"user":{"__type":"Pointer","className":"_User","objectId":"6vlpznuyEz"},"topic":{"__type":"Pointer","className":"Topic","objectId":"82RPtkE6aF"} }' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/UserTopic




PARSE_HOST=localhost
PARSE_PORT=23740
PARSE_APP=DRIP


function createParseUser(){

    username="$1"
    password="$2"
    email="$3"
    
    curl -X POST \
      -H "X-Parse-Application-Id: ${PARSE_APP}" \
      -H "Content-Type: application/json" \
      -d "{\"username\":\"${username}\",\"password\":\"${password}\",\"email\":\"${email}\"}" \
      http://$PARSE_HOST:$PARSE_PORT/parse/classes/_User
}

createParseUser johnsmith johnsmith1 "johnsmith@o.com"

function createTopic(){

    name="$1"
    type="$2"
    curl -X POST \
      -H "X-Parse-Application-Id: DRIP" \
      -H "Content-Type: application/json" \
      -d "{\"name\":\"${name}\",\"type\":\"${type}\"}" \
      http://$PARSE_HOST:$PARSE_PORT/parse/classes/Topic
}

createTopic Sport category

function createUserTopic(){
    
    userId="$1"
    topicId="$2"
    curl -X POST \
      -H "X-Parse-Application-Id: DRIP" \
      -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
      -H "Content-Type: application/json" \
      -d "{\"user\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${userId}\"},\"topic\":{\"__type\":\"Pointer\",\"className\":\"Topic\",\"objectId\":\"${topicId}\"} }" \
      http://$PARSE_HOST:$PARSE_PORT/parse/classes/UserTopic

}

createUserTopic VDjgwRpbZK czMOgWNiug
createUserTopic VDjgwRpbZK hEGXFKZTdC


function createPost(){

    createdByUserId="$1"
    title="$2"
    body="$3"
    type="$4"
    topic="$5"
    image="$6"
    curl -X POST \
      -H "X-Parse-Application-Id: DRIP" \
      -H "Content-Type: application/json" \
      -d "{\"title\":\"${title}\",\"body\":\"${body}\",\"type\":\"${type}\"\"images\":[\"${image}\"],\"topics\":[{\"__type\":\"Pointer\",\"className\":\"Topic\",\"objectId\":\"${topic}\"}],\"createdBy\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${createdByUserId}\"}}" \
      http://$PARSE_HOST:$PARSE_PORT/parse/classes/Post
}


createPost LwGyWLEJ2E "Apple iFag" "Check out the new Apple iFag!!!!" brand 82RPtkE6aF

createPost 6vlpznuyEz "Apple iFag" "Check out the new Apple iFag!!!!" user 82RPtkE6aF "http://vignette2.wikia.nocookie.net/freeciv/images/3/3b/Apple_logo.png"

