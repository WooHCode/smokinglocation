REPOSITORY=build/libs

echo "> cd $REPOSITORY"
cd $REPOSITORY

echo "> Finding the currently running app's PID"
CURRENT_PID=$(pgrep -f smoking)
echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> No running app."
else
    echo "> kill -9 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 3
fi

echo "> Deploying the new app"

echo "> nohup java -jar smokinglocation-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &"
nohup java -jar smokinglocation-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &

echo "> finish"
echo ">test"
