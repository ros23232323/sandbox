#!/bin/sh

export NODE_ENV=test
#for raceObjectId in $(mongo localhost:27017/local --quiet --eval "db.getCollection('Meeting').find({'track':'Galway'},{'races':1}).pretty()" | jq '.races[].objectId' | tr -d "\"" | head -n 5)
#do
#  echo $raceObjectId
#  node ./src/raceCollect.js $raceObjectId
#done


node ./src/raceCollect.js nHYQEhOQGY
