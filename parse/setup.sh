APPLICATION_ID=$(uuid)
MASTER_KEY=$(uuid)
APPLICATION_ID=test
MASTER_KEY=test
npm install -g parse-server mongodb-runner
mongodb-runner start
parse-server --appId $APPLICATION_ID --masterKey $MASTER_KEY
parse-dashboard --appId $APPLICATION_ID --masterKey $MASTER_KEY --serverURL "http://localhost:1337/parse" --appName horsetracker



com.lucidlogic.horsetracker.androidboilerplate
