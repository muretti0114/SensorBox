#!/bin/sh
# SensorBoxServiceClient.pl の稼動状態をチェックする
# 11秒間隔をあけてcurrent.logを読み込み，変化がなかったらおかしい

dir=/root/SensorBoxServiceClient/
log=$dir/sensorboxclient.out

echo -n "Checking logger status ... "
now=`cat $log`  
sleep 11
next=`cat $log`

if [ "$now" != "$next" ]; then
	echo "success: logging process in progress."
else
	echo "fail: logging process stopped!?"
fi

