PARSE_HOST=10.10.2.13
PARSE_PORT=23740
PARSE_APP=DRIP
PARSE_MASTER_KEY=DEVMASTERKEY 

curl -X GET \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -G \
  --data-urlencode 'username=test' \
  --data-urlencode 'password=test' \
  http://$PARSE_HOST:$PARSE_PORT/parse/login


for user in "damien" "ray" "finola" "ger"
do
user=test
curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"$user\",\"password\":\"$user\",\"email\":\"$user@o.com\"}" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/_User | jq '.'
echo $user
done


curl -X GET \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas/UserProfile | jq '.'

#create class Topic
curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{  "className": "Topic",  "fields": {    "name": {      "type": "String"    },    "type": {      "type": "String"    },    "image": {      "type": "File"    }  }}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas | jq '.'


#delete class Topic
curl -X DELETE \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas/Topic | jq '.'



#create class Post
curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${PARSE_MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{  "className": "Post",  "fields": {    "body": {      "type": "String"    },    "title": {      "type": "String"    }, "createdAt": {      "type": "Date"    },"createdBy": {      "type": "Pointer", "className":"User"    },    "image": {      "type": "File"    }  }}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/schemas | jq '.'



function addUserFollow(){

user=$1
follows=$2

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d "{\"user\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${user}\"},\"follows\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${follows}\"}}" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/UserFollows | jq '.'

curl -X POST \
  -H "X-Parse-Application-Id: DRIP" \
  -H "Content-Type: application/json" \
  -d "{\"user\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${follows}\"},\"followedBy\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"${user}\"}}" \
  http://$PARSE_HOST:$PARSE_PORT/parse/classes/UserFollowedBy | jq '.'

}



addUserFollow 3lZ2jyObbx YwIxRgFe2X
addUserFollow 3lZ2jyObbx HhYAZ7OGns


curl -X POST \
  -H "X-Parse-Application-Id: ${PARSE_APP}" \
  -H "X-Parse-Master-Key: ${MASTER_KEY}" \
  -H "Content-Type: application/json" \
  -d '{"userId":"3lZ2jyObbx","limit":100,"skip":0}' \
  http://$PARSE_HOST:$PARSE_PORT/parse/functions/timeline | jq '.'

