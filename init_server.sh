echo "> 기존 pid 확인"
        CURRENT_PID=$(ps -ef | grep java | grep smokinglocation | grep -v nohup | awk '{print $2}')echo "$CURRENT_PID"
        if [ -z ${CURRENT_PID} ] ;then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
        else
        echo "> sudo kill -9 $CURRENT_PID"
        sudo kill -9 $CURRENT_PID
        sleep 10
        fi
        echo "> smokinglocation 배포"
        echo "> test"