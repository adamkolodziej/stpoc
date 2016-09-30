

set ROOT=%CD%
set CATALINA_HOME=%ROOT%\apache-tomcat
set JAVA_OPTS=%JAVA_OPTS% -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=356m

apache-tomcat\bin\shutdown.bat
