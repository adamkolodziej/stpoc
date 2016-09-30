#!/bin/sh

export ROOT=$PWD
export CATALINA_HOME=$ROOT/apache-tomcat
export JAVA_OPTS="$JAVA_OPTS -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=356m"

echo "core plus root: $ROOT:"
echo "catalina home: $CATALINA_HOME"
echo "JAVA_OPTS=$JAVA_OPTS"

chmod u+x $CATALINA_HOME/bin/*.sh

$CATALINA_HOME/bin/shutdown.sh
