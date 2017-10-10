#!/bin/sh
# made by ChaoYF

export PATH=/data/cc/bin:$PATH
export LD_LIBRARY_PATH=/data/cc/lib:$LD_LIBRARY_PATH

mount -o remount,rw rootfs /

mkdir /tmp
mount -t tmpfs -o size=4K,mode=0755 tmpfs /tmp
chmod 777 /tmp

# add my soft  by ChaoYF
mkdir /data/ChaoYF
mkdir /data/ChaoYF/App_Linux
mkdir /data/ChaoYF/App_Data
mkdir /data/ChaoYF/App_Var
mkdir /data/ChaoYF/App_WebServer
mkdir /data/ChaoYF/App_Var/Log
mkdir /data/ChaoYF/App_Var/Data

mount -t tmpfs -o size=8M,mode=0755 tmpfs /data/ChaoYF/App_Var

chmod 777 /data/ChaoYF
chmod 777 /data/ChaoYF/App_Linux
chmod 777 /data/ChaoYF/App_Data
chmod 777 /data/ChaoYF/App_Var
chmod 777 /data/ChaoYF/App_WebServer
chmod 777 /data/ChaoYF/App_Var/Log
chmod 777 /data/ChaoYF/App_Var/Data
#sh /data/ChaoYF/App_Linux/App_init.sh
# my soft  add  end!


mount -o remount,ro rootfs /
chmod 777 /system/data


setprop service.adb.tcp.port 5555
stop adbd && start adbd

chmod 777 /data/cc/*

#启动硬件开门狗
#sh /data/cc/sDog.sh &
#chmod 777 /data/ChaoYF/App_Linux/*
chmod 777 /data/ChaoYF/App_Linux/manager/sDog.sh
sh /data/ChaoYF/App_Linux/manager/sDog.sh &
#启动应用脚本
chmod 777 /data/ChaoYF/App_Linux/App_init.sh
sh /data/ChaoYF/App_Linux/App_init.sh

sleep 10
#启动 开机刷机脚本
superfile='/mnt/usbhost/super_usr.sh'
chmod 777 $superfile
#多次时间 判断 开机u盘插入
if [ -z "`ls $superfile`" ]
then
    echo "now usbhost not $superfile"
	#等待 20s 再次判断
	sleep 20
	if [ -z "`ls $superfile`" ]
	then
		echo "now usbhost not $superfile"
		#等待 20s 再次判断
		sleep 30
		if [ -z "`ls $superfile`" ]
		then
			echo "now usbhost not $superfile"
		else
			sh $superfile &
		fi
	else
		sh $superfile &
	fi
else
	sh $superfile &
fi


