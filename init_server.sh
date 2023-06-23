REPOSITORY=/home/ec2-user/smok/build/libs
cd build/libs/

echo "> jar 위치  : $REPOSITORY"
echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f jar)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 15
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls $REPOSITORY/ |grep 'smoking' | tail -n 1)

echo "> JAR Name: $JAR_NAME"

sudo nohup java -jar $REPOSITORY/$JAR_NAME > /dev/null 2>&1 &

