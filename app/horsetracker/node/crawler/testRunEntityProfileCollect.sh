#!/bin/sh

PARSE_HOST=$(ifconfig wlp2s0 | grep 'inet addr:' | cut  -d: -f2 | awk '{print $1}')
PARSE_PORT=1337
PARSE_HOST_LOCAL=localhost
PARSE_APP=HorseTracker
APPLICATION_ID=$(echo -n $PARSE_APP | md5sum | awk '{print $1}')
MASTER_KEY=$APPLICATION_ID

#delete all EntityDetail
# for tbl in EntityDetailHistorical EntityDetailFuture
# do
#   for objectId in $(curl -s -X GET  -H "X-Parse-Master-Key: ${MASTER_KEY}" -H "X-Parse-Application-Id: ${APPLICATION_ID}"   -H "Content-Type: application/json"   http://$PARSE_HOST:$PARSE_PORT/parse/classes/$tbl | jq '.[] | .[].objectId' | tr -d "\"")
#   do
#     curl -X DELETE \
#       -H "X-Parse-Application-Id: ${APPLICATION_ID}" \
#       -H "Content-Type: application/json" \
#       http://$PARSE_HOST:$PARSE_PORT/parse/classes/$tbl/$objectId
#   done
# done
#
# curl -X DELETE \
#   -H "X-Parse-Application-Id: ${APPLICATION_ID}" \
#   -H "X-Parse-Master-Key: ${MASTER_KEY}" \
#   -H "Content-Type: application/json" \
#   http://$PARSE_HOST:$PARSE_PORT/parse/classes/EntityDetail

node ./src/entityCollect.js skKSB09EB0
