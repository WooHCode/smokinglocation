REPOSITORY=/smok/build/libs

cd $REPOSITORY

echo "> now ing app pid find!"

CURRENT_PID=$(pgrep -f smoking)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> no ing app."
else
    echo "> kill -9 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 3
fi
echo "> new app deploy"

JAR_NAME=$(ls $REPOSITORY/ |grep 'smokinglocation-0.0.1-SNAPSHOT.jar' | tail -n 1)

echo "> JAR Name: $JAR_NAME"

JAVA=/usr/lib/jvm/java-11-amazon-corretto.x86_64/bin/java

nohup $JAVA -jar $REPOSITORY/$JAR_NAME &