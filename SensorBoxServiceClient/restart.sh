#!/bin/sh
# SensorBoxServiceClient.pl を再起動する

dir=/root/SensorBoxServiceClient/

cd $dir

./stop.sh

echo  -n "Restarting logger..."
rm   nohup.out
nohup perl SensorBoxServiceClient.pl &
echo  "Done."

