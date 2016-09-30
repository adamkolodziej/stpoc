#!/bin/bash

if [ ! -d "mongo-data" ]; then
	mkdir mongo-data
fi

# http://stackoverflow.com/questions/27375137/failed-global-initialization-badvalue-invalid-or-no-user-locale-set-please-ens
export LC_ALL=C

# download mongodb for MAC OS
UNAME=$(uname -s)

if [ "$UNAME" = "Darwin" ]; then
	if [ ! -d "mongodb-osx" ]; then
		curl https://fastdl.mongodb.org/osx/mongodb-osx-x86_64-3.0.7.tgz -o mongo.tgz
		gunzip -c mongo.tgz | tar xvf -
		rm mongo.tgz
		mv mongodb-osx-x86_64-3.0.7 mongodb-osx
	fi
	cd mongodb-osx/bin
elif [ "$UNAME" = "Linux" ]; then
	cd mongodb-linux/bin
else
	echo "No Linux, or MAC detected, nothing to do..."
fi

if [ ! -f "../../mongod.log" ]; then
	touch ../../mongod.log
	chmod a+rwx ../../mongod.log
fi

#./mongod --dbpath ../../mongo-data &
./mongod --dbpath ../../mongo-data --logappend --logpath ../../mongod.log --fork &
disown
