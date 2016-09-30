#!/bin/bash

logger "cloudpreinstall.sh was executed, working dir: $PWD"
echo "cloudpreinstall.sh was executed, working dir: $PWD"

export HYBRIS_ROOT=$PWD
export MONGO_DIR=$HYBRIS_ROOT/coreplus/mongodb-linux
export CATALINA_HOME=$PWD/coreplus/apache-tomcat

if [ -d coreplus ]; then

	if [ -e /etc/init.d/mongod ]; then
		logger "stopping system mongod"
		echo "Wepovu64" | sudo -S /etc/init.d/mongod stop
	fi

	mongo_pid=$(pidof mongod)
	if [ "$mongo_pid" != "" ]; then
		logger "some mongo still running, probably previously started. stopping"
		kill $mongo_pid
	fi

	cd coreplus/
	chmod u+x $MONGO_DIR/bin/*
	chmod u+x *.sh
	logger "starting embedded mongod"
	./start_mongo.sh

	if [ -d "$CATALINA_HOME" ]; then
                nohup ./start_tomcat.sh > tomcat.log 2>&1 &
	fi

	# CECS-348 CEI mock by default in yDC
	if [ -e ceimock.jar ]; then
                nohup ./start_ceimock.sh > ceimock.log 2>&1 &
	fi
fi

cd $HYBRIS_ROOT

logger "resetting DataHub database..."
DB_NAME=integration
mysql -u root -proot -e "drop database if exists $DB_NAME"
mysql -u root -proot -e "CREATE SCHEMA $DB_NAME DEFAULT CHARACTER SET utf8 COLLATE utf8_bin"
#mysql -u root -proot -D "$DB_NAME" < sbg.dump

chmod u+x *.sh
./update_sampledata.sh
