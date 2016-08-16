PARSE_HOST=$(ifconfig wlp2s0 | grep 'inet addr:' | cut  -d: -f2 | awk '{print $1}')
PARSE_HOST_LOCAL=localhost
PARSE_APP=HorseTracker
PARSE_PORT=1337
#CLOUD_DIR=/home/ian/Documents/sandbox/app/horsetracker/node/cloud
#CLOUD_DIR=/home/itowey/Documents/sandbox/sandbox/app/horsetracker/node/cloud
CLOUD_DIR=/home/itowey/Documents/sandbox/sandbox/app/horsetracker/node/cloud
cd $CLOUD_DIR
APPLICATION_ID=$(echo -n $PARSE_APP | md5sum | awk '{print $1}')
#APPLICATION_ID=$PARSE_APP
MASTER_KEY=$APPLICATION_ID
export NODE_PATH=/usr/local/lib/node_modules
#sudo npm install -g parse-server mongodb-runner parse-dashboard parse
mongodb-runner start &
sleep 10
MONGO_URI=mongodb://localhost:27017/local
parse-server --databaseURI $MONGO_URI --appId $APPLICATION_ID --masterKey $MASTER_KEY --restAPIKey $MASTER_KEY --cloud $CLOUD_DIR/main.js --port $PARSE_PORT & 
sleep 5
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://$PARSE_HOST_LOCAL:$PARSE_PORT/parse" --appName $PARSE_APP &
cd ../crawler
atom .


curl -s -X POST \
  -H "X-Parse-Application-Id: $APPLICATION_ID" \
  -H "X-Parse-REST-API-Key: $MASTER_KEY" \
  -H "Content-Type: application/json" \
  http://localhost:1337/parse/functions/hello

curl -s -X POST \
  -H "X-Parse-Application-Id: $APPLICATION_ID" \
  -H "X-Parse-REST-API-Key: $MASTER_KEY" \
  -H "Content-Type: application/json" \
  http://localhost:1337/parse/functions/hello


curl -X POST \
  -H "X-Parse-Application-Id: $APPLICATION_ID" \
  -H "X-Parse-REST-API-Key: $MASTER_KEY" \
  -H "Content-Type: application/json" \
  -d '{"uniqueCol":"1234567-ADFGHJK"}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Blah




Parse.initialize('9c188b00235ac01ec4bff34fcd3f7838', '9c188b00235ac01ec4bff34fcd3f7838');
Parse.serverURL = 'http://192.168.1.102:1337/parse';



node node_modules/parse-dashboard/bin/parse-dashboard --appId DRIP --serverURL "http://localhost:23740/parse" --appName DRIP --allowInsecureHTTP true --masterKey DEVMASTERKEY &
node node_modules/parse-dashboard/bin/parse-dashboard --appId DRIP --serverURL "http://localhost:23740/parse" --appName DRIP --allowInsecureHTTP true --masterKey DEVMASTERKEY &

#load racecards
/home/ian/Documents/sandbox/app/horsetracker/node/crawler/src
node app.js


node node_modules/mongodb-runner/bin/mongodb-runner
node ../drip-server/src/server.js
node node_modules/parse-dashboard/bin/parse-dashboard --config parse-dashboard-config.json --allowInsecureHTTP true

#eg parse-dashboard-config.json
{
  "apps": [
    {
      "serverURL": "http://nodeweb01.androidapp.dev.ostk.com:23741/parse",
      "appId": "DRIP",
      "masterKey": "DEVMASTERKEY"
    }
  ],
   "users": [
    {
      "user":"admin",
      "pass":"admin"
    }
  ]
}



APPLICATION_ID=DRIP
MASTER_KEY=DRIP
npm install -g parse-server mongodb-runner parse-dashboard
mongodb-runner start &
sleep 5
parse-server --appId $APPLICATION_ID --masterKey $MASTER_KEY --clientKey $MASTER_KEY --cloud ~/Documents/sandbox/sandbox/local-parse/main.js & 
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://localhost:23740/parse" --appName $APPLICATION_ID &
sleep 5
sleep 5

parse-dashboard --appId DRIP --masterKey DEVMASTERKEY --serverURL "http://10.10.2.69:23740/parse" --appName DRIP &



Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world, Ian!");
});

parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://10.10.2.69:1337/parse" --appName demo &

sudo apt-get install jq
#delete all Race
for objectId in $(for objectId in $(curl -s -X GET -H "X-Parse-Master-Key: ${MASTER_KEY}"  -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/Race | jq '.[] | .[].objectId' | tr -d "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Race/$objectId
done
 "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Race/$objectId
done

#delete all Meetings
for objectId in $(curl -s -X GET  -H "X-Parse-Master-Key: ${MASTER_KEY}" -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/Meeting | jq '.[] | .[].objectId' | tr -d "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Meeting/$objectId
done

for objectId in $(curl -s -X GET   -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/Racecard | jq '.[] | .[].objectId' | tr -d "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Racecard/$objectId
done

curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas/Meeting

curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas/Race

curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas/Racecard



#delete all Racecard
for objectId in $(curl -s -X GET   -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/Racecard | jq '.[] | .[].objectId' | tr -d "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Racecard/$objectId
done




PARSE_HOST=localhost
PARSE_PORT=1337
PARSE_APP=HorseTracker
curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "X-Parse-REST-API-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"body":"Blah blah blah ....","title":"Check out my new boot"}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Blah


PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
MASTER_KEY=DEVMASTERKEY
PARSE_PORT=23740
PARSE_APP=DRIP
#call cloud function
PARSE_HOST=nodeweb01.iosoapp.dev.ostk.com
PARSE_HOST=localhost
PARSE_PORT=23740

curl -X POST \
  -H "X-Parse-Application-Id: $PARSE_APP" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/functions/hello
PARSE_PORT=1337
PARSE_APP=test


curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{}' \
  http://$PARSE_APP_HOST:$PARSE_APP_PORT/parse/functions/hello
  "http://$PARSE_HOST:$PARSE_PORT/parse/functions/timeline?userId=m6RLkdTsAK&limit=100&skip=0"




curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"userId":"ne5NsagzaR","limit":1000,"skip":0}' \
  http://nodeweb01.iosdriptest.dev.ostk.com:23740/parse | jq '.'

PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
MASTER_KEY=DEVMASTERKEY
PARSE_PORT=23740
PARSE_APP=DRIP
USER_ID=5HtWQji3By
curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d "{\"userId\":\"${USER_ID}\",\"limit\":100,\"skip\":0}" \
  http://$PARSE_HOST:$PARSE_PORT/parse/functions/timeline

curl -X GET \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://localhost:$PARSE_PORT/parse/classes/_User

curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Meeting

curl -X GET \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Meeting

PARSE_HOST=localhost
PARSE_PORT=23740
PARSE_APP=DRIP
PARSE_MASTER_KEY=DEVMASTERKEY 


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


#Create Users 
for idx in a b c d e f g
do
PASSWORD=$(printf "$idx%.0s" 1 2 3 4 5)
curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"$idx\",\"password\":\"aaaaa\",\"email\":\"$idx@drip.com\"}" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/_User
done

#List all users
curl -s -X GET   -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/_User | jq '.[] | .[].objectId' | tr -d "\""

#List follow user
function addUserFollow(){

user=$1
follows=$2

curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d "{\"user\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${user}\"},\"follows\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${follows}\"} }" \
  http://localhost:23740/parse/classes/UserFollows

}

    clear
    PARSE_APP=DRIP
    PARSE_MASTER_KEY=DEVMASTERKEY 
    PARSE_HOST=localhost
    PARSE_PORT=23740
    USER_ID=LY55c39dDr
    curl -s -X POST \
      -H "X-Parse-Application-Id: ${PARSE_APP}" \
      -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
      -H "Content-Type: application/json" \
      -d "{\"userId\":\"${USER_ID}\",\"limit\":100,\"skip\":0}" \
      http://$PARSE_HOST:$PARSE_PORT/parse/functions/userfollowing | jq '.[]'


clear
PARSE_APP=DRIP
PARSE_MASTER_KEY=DEVMASTERKEY 
PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
PARSE_PORT=23740
CUR_USER_ID=F6sul8Ejw0
OTHER_USER_ID=kpXwGv2Gzd
curl -s -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d "{\"currentUserId\":\"${CUR_USER_ID}\",\"otherUserId\":\"${OTHER_USER_ID}\",\"limit\":100,\"skip\":0}" \
  http://$PARSE_HOST:$PARSE_PORT/parse/functions/otherUserFollowing | jq '.[]'



#List network
curl -X GET \
-H "X-Parse-Application-Id: ${PARSE_APP}" \
-H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
-H "Content-Type: application/json" \
http://localhost:23740/parse/classes/UserFollows


curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d "{\"user\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${follows}\"},\"followedBy\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${user}\"} }" \
  http://localhost:23741/parse/classes/UserFollowedBy



addUserFollow vwmKdgXv20 iFU8Isbq7F



curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{"user":{"__type":"Pointer","className":"_User","objectId":"6vlpznuyEz"},"topic":{"__type":"Pointer","className":"Topic","objectId":"82RPtkE6aF"} }' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/UserTopic




PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
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
      "http://$PARSE_HOST:$PARSE_PORT/parse/classes/Topic"
}

function createTopic(){

PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
PARSE_PORT=23740
PARSE_APP=DRIP
MASTER_KEY=DEVMASTERKEY
APPLICATION_ID=DRIP

name="$1"
type="$2"
TOPIC_ID=$(curl -s -X POST -H "X-Parse-Application-Id: DRIP"  -H "Content-Type: application/json" -d "{\"name\":\"${name}\",\"type\":\"${type}\"}" "http://$PARSE_HOST:$PARSE_PORT/parse/classes/Topic" | jq '.objectId' | tr -d "\"")
echo "Topic created $TOPIC_ID"
FILE_PATH="$3"
FILE_NAME="$(basename $FILE_PATH)"
PARSE_FILE_NAME=$(curl -s -X POST -H "X-Parse-Application-Id: ${APPLICATION_ID}" -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" -H "Content-Type: image/jpeg" --data-binary "@$FILE_PATH" "http://$PARSE_HOST:$PARSE_PORT/parse/files/$FILE_NAME" | jq '.name' | tr -d "\"")
echo "File uploaded $PARSE_FILE_NAME"
curl -s -X PUT -H "X-Parse-Application-Id: ${PARSE_APP}" -H "X-Parse-Master-Key: ${MASTER_KEY}" -H "Content-Type: application/json" -d "{\"image\": {\"name\": \"$PARSE_FILE_NAME\",\"__type\": \"File\"}}" "http://$PARSE_HOST:$PARSE_PORT/parse/classes/Topic/$TOPIC_ID"
echo "Topic image updated "
}

createTopic tmp tmp "Desktop/panasonic_416x416.jpg"
createTopic "Michael Kors" brand "Desktop/michael-kors-holdings_416x416.jpg"
createTopic "Ralph Lauren" brand "Desktop/ralph-lauren_416x416.jpg"

createTopic Electronics category
createTopic Gardening category
createTopic Home category
createTopic Prada brand
createTopic Nike brand
createTopic Samsung brand
createTopic Adidas brand
createTopic Prada brand


createTopic Apple brand



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



$ANDROID_HOME/platform-tools/adb -s emulator-5554 shell "pm uninstall com.lucidlogic.horsetracker"
 1989  $ANDROID_HOME/platform-tools/adb push /home/ian/Documents/sandbox/app/horsetracker/android/Horsetracker/app/build/outputs/apk/app-debug.apk /data/local/tmp/com.lucidlogic.horsetracker
 1990  $ANDROID_HOME/platform-tools/adb shell pm install -r "/data/local/tmp/com.lucidlogic.horsetracker"
 1991  $ANDROID_HOME/platform-tools/adb shell am start -n "com.lucidlogic.horsetracker/com.lucidlogic.horsetracker.activity.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
 1992  $ANDROID_HOME/platform-tools/adb -s emulator-5554 shell "pm uninstall com.lucidlogic.horsetracker"


*************************************************************************************************************************************
*************************************************************************************************************************************
*************************************************************************************************************************************
*************************************************************************************************************************************
*************************************************************************************************************************************
*************************************************************************************************************************************
*************************************************************************************************************************************
*************************************************************************************************************************************










cd Documents/sandbox/local-parse
PARSE_HOST=$(ifconfig wlp2s0 | grep 'inet addr:' | cut  -d: -f2 | awk '{print $1}')
PARSE_HOST_LOCAL=localhost
PARSE_APP=HorseTrackere
PARSE_PORT=1337
CLOUD_DIR=/home/ian/Documents/sandbox/horsetracker-parse
APPLICATION_ID=$(echo -n $PARSE_APP | md5sum | awk '{print $1}')
#APPLICATION_ID=$PARSE_APP
MASTER_KEY=$APPLICATION_ID
export NODE_PATH=/usr/local/lib/node_modules
#sudo npm install -g parse-server mongodb-runner parse-dashboard parse
mongodb-runner start &
sleep 10
parse-server --appId $APPLICATION_ID --masterKey $MASTER_KEY --restAPIKey $MASTER_KEY --cloud $CLOUD_DIR/main.js --port $PARSE_PORT & 
sleep 5
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://$PARSE_HOST_LOCAL:$PARSE_PORT/parse" --appName $PARSE_APP &

node node_modules/parse-dashboard/bin/parse-dashboard --appId DRIP --serverURL "http://localhost:23740/parse" --appName DRIP --allowInsecureHTTP true --masterKey DEVMASTERKEY &
node node_modules/parse-dashboard/bin/parse-dashboard --appId DRIP --serverURL "http://localhost:23740/parse" --appName DRIP --allowInsecureHTTP true --masterKey DEVMASTERKEY &

#load racecards
/home/ian/Documents/sandbox/app/horsetracker/node/crawler/src
node app.js


node node_modules/mongodb-runner/bin/mongodb-runner
node ../drip-server/src/server.js
node node_modules/parse-dashboard/bin/parse-dashboard --config parse-dashboard-config.json --allowInsecureHTTP true

#eg parse-dashboard-config.json
{
  "apps": [
    {
      "serverURL": "http://nodeweb01.androidapp.dev.ostk.com:23741/parse",
      "appId": "DRIP",
      "masterKey": "DEVMASTERKEY"
    }
  ],
   "users": [
    {
      "user":"admin",
      "pass":"admin"
    }
  ]
}



APPLICATION_ID=DRIP
MASTER_KEY=DRIP
npm install -g parse-server mongodb-runner parse-dashboard
mongodb-runner start &
sleep 5
parse-server --appId $APPLICATION_ID --masterKey $MASTER_KEY --clientKey $MASTER_KEY --cloud ~/Documents/sandbox/sandbox/local-parse/main.js & 
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://localhost:23740/parse" --appName $APPLICATION_ID &
sleep 5
sleep 5

parse-dashboard --appId DRIP --masterKey DEVMASTERKEY --serverURL "http://10.10.2.69:23740/parse" --appName DRIP &

curl -X POST \
  -H "X-Parse-Application-Id: $PARSE_APP" \
  -H "X-Parse-REST-API-Key: $MASTER_KEY" \
  -H "Content-Type: application/json" \
  http://localhost:1337/parse/functions/hello


Parse.Cloud.define("hello", function(request, response) {
  response.success("Hello world, Ian!");
});

parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://10.10.2.69:1337/parse" --appName demo &

sudo apt-get install jq
#delete all Race
for objectId in $(for objectId in $(curl -s -X GET -H "X-Parse-Master-Key: ${MASTER_KEY}"  -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/Race | jq '.[] | .[].objectId' | tr -d "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Race/$objectId
done
 "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Race/$objectId
done

#delete all Meetings
for objectId in $(curl -s -X GET  -H "X-Parse-Master-Key: ${MASTER_KEY}" -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/Meeting | jq '.[] | .[].objectId' | tr -d "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Meeting/$objectId
done

for objectId in $(curl -s -X GET   -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/Racecard | jq '.[] | .[].objectId' | tr -d "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Racecard/$objectId
done

curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas/Meeting

curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas/Race

curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas/Racecard



#delete all Racecard
for objectId in $(curl -s -X GET   -H "X-Parse-Application-Id: ${PARSE_APP}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/Racecard | jq '.[] | .[].objectId' | tr -d "\"")
do
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Racecard/$objectId
done




PARSE_HOST=nodeweb01.androidapp.dev.ostk.com
PARSE_PORT=23741
PARSE_APP=DRIP
curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  -d '{"body":"Blah blah blah ....","title":"Check out my new boot", "images":["http://www.prodirectsoccer.com/productimages/V3_1_Main/117574.jpg"],"type":"user","topics":[{"__type":"Pointer","className":"Topic","objectId":"NBUxduhSXs"}],"createdBy":{"__type":"Pointer","className":"_User","objectId":"vwmKdgXv20"}}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Post


PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
MASTER_KEY=DEVMASTERKEY
PARSE_PORT=23740
PARSE_APP=DRIP
#call cloud function
PARSE_HOST=nodeweb01.iosoapp.dev.ostk.com
PARSE_HOST=localhost
PARSE_PORT=23740

curl -X POST \
  -H "X-Parse-Application-Id: $PARSE_APP" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/functions/hello
PARSE_PORT=1337
PARSE_APP=test


curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d '{}' \
  http://$PARSE_APP_HOST:$PARSE_APP_PORT/parse/functions/hello
  "http://$PARSE_HOST:$PARSE_PORT/parse/functions/timeline?userId=m6RLkdTsAK&limit=100&skip=0"




curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"userId":"ne5NsagzaR","limit":1000,"skip":0}' \
  http://nodeweb01.iosdriptest.dev.ostk.com:23740/parse | jq '.'

PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
MASTER_KEY=DEVMASTERKEY
PARSE_PORT=23740
PARSE_APP=DRIP
USER_ID=5HtWQji3By
curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d "{\"userId\":\"${USER_ID}\",\"limit\":100,\"skip\":0}" \
  http://$PARSE_HOST:$PARSE_PORT/parse/functions/timeline

curl -X GET \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://localhost:1337/parse/classes/_User

curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Meeting

curl -X GET \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/Meeting

PARSE_HOST=10.10.2.13
PARSE_PORT=23740
PARSE_APP=DRIP
PARSE_MASTER_KEY=DEVMASTERKEY 

curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"username":"john smith","password":"js","email":"js@o.com"}' \
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




PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
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
      "http://$PARSE_HOST:$PARSE_PORT/parse/classes/Topic"
}

function createTopic(){

PARSE_HOST=nodeweb01.androiddrip.dev.ostk.com
PARSE_PORT=23740
PARSE_APP=DRIP
MASTER_KEY=DEVMASTERKEY
APPLICATION_ID=DRIP

name="$1"
type="$2"
TOPIC_ID=$(curl -s -X POST -H "X-Parse-Application-Id: DRIP"  -H "Content-Type: application/json" -d "{\"name\":\"${name}\",\"type\":\"${type}\"}" "http://$PARSE_HOST:$PARSE_PORT/parse/classes/Topic" | jq '.objectId' | tr -d "\"")
echo "Topic created $TOPIC_ID"
FILE_PATH="$3"
FILE_NAME="$(basename $FILE_PATH)"
PARSE_FILE_NAME=$(curl -s -X POST -H "X-Parse-Application-Id: ${APPLICATION_ID}" -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" -H "Content-Type: image/jpeg" --data-binary "@$FILE_PATH" "http://$PARSE_HOST:$PARSE_PORT/parse/files/$FILE_NAME" | jq '.name' | tr -d "\"")
echo "File uploaded $PARSE_FILE_NAME"
curl -s -X PUT -H "X-Parse-Application-Id: ${PARSE_APP}" -H "X-Parse-Master-Key: ${MASTER_KEY}" -H "Content-Type: application/json" -d "{\"image\": {\"name\": \"$PARSE_FILE_NAME\",\"__type\": \"File\"}}" "http://$PARSE_HOST:$PARSE_PORT/parse/classes/Topic/$TOPIC_ID"
echo "Topic image updated "
}

createTopic tmp tmp "Desktop/panasonic_416x416.jpg"
createTopic "Michael Kors" brand "Desktop/michael-kors-holdings_416x416.jpg"
createTopic "Ralph Lauren" brand "Desktop/ralph-lauren_416x416.jpg"

createTopic Electronics category
createTopic Gardening category
createTopic Home category
createTopic Prada brand
createTopic Nike brand
createTopic Samsung brand
createTopic Adidas brand
createTopic Prada brand


createTopic Apple brand



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



$ANDROID_HOME/platform-tools/adb -s emulator-5554 shell "pm uninstall com.lucidlogic.horsetracker"
 1989  $ANDROID_HOME/platform-tools/adb push /home/ian/Documents/sandbox/app/horsetracker/android/Horsetracker/app/build/outputs/apk/app-debug.apk /data/local/tmp/com.lucidlogic.horsetracker
 1990  $ANDROID_HOME/platform-tools/adb shell pm install -r "/data/local/tmp/com.lucidlogic.horsetracker"
 1991  $ANDROID_HOME/platform-tools/adb shell am start -n "com.lucidlogic.horsetracker/com.lucidlogic.horsetracker.activity.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
 1992  $ANDROID_HOME/platform-tools/adb -s emulator-5554 shell "pm uninstall com.lucidlogic.horsetracker"
