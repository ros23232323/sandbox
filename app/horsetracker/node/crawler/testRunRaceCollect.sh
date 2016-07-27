#!/bin/sh

export NODE_ENV=test
for raceObjectId in SOX5iuKPlk pSiEpLNBOX 2xUpCRwxRj 5C4gpsQAY1 caHbQu6iUC
do
node ./src/raceCollect.js $raceObjectId
done
