#!/bin/sh

APP_NAME="0.1"
PROCESS_PATTERN="${process.grep.pattern}"
PID=$(ps -ef | grep $PROCESS_PATTERN | grep -v grep | awk '{print $2}')

if [ -z $PID ];
then
  logger -s "Can't find a matching process!  pattern='$PROCESS_PATTERN'"
  exit;
else
  logger -t $APP_NAME "Shutdown Initiated (pid=$PID)"
  kill $PID
  echo -n "Waiting for $APP_NAME ($PID) to die"
  while ( ps -e | grep $PID > /dev/null )
  do
    echo -n "."
    sleep 1
  done
  echo
fi

