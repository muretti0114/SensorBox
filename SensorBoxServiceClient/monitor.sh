#!/bin/sh
# SensorBoxServiceClient.plの状況を監視し，沈黙していたら再起動をかける

LANG=C
dir=/root/SensorBoxServiceClient/

cd $dir

if ./check.sh | grep fail; then
	echo "$0: something wrong. restart now."
	./restart.sh
else 
	echo "$0: check OK on `date`"
fi

