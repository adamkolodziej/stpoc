
set JPDA_ADDRESS=8801
set JPDA_TRANSPORT=dt_socket
set JPDA_SUSPEND=n
set JAVA_OPTS=-agentlib:jdwp=transport=%JPDA_TRANSPORT%,address=%JPDA_ADDRESS%,server=y,suspend=%JPDA_SUSPEND%

set ROOT=%CD%
set CATALINA_HOME=%ROOT%\apache-tomcat
set JAVA_OPTS=%JAVA_OPTS% -Xms2048m -Xmx4096m -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+AlwaysPreTouch -XX:+DisableExplicitGC
rem CECS-384 consistent locale settings
set JAVA_OPTS=%JAVA_OPTS% -Duser.language=en -Duser.country=US -Duser.region=US -Duser.timezone=CET

apache-tomcat\bin\startup.bat jpda start
