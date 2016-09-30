# copy our custom local.properties and localextensions.xml
cp -f git-repo-download/ci/${ciconfig}/local.properties 56cecs/config/
cp -f git-repo-download/ci/${ciconfig}/localextensions.xml 56cecs/config/

# changing standard database name to name with prefix
# if database occurs more then once (e.g. in comments) then it will be overwrite in each occurance
TEMP_DB="db.url=jdbc:mysql://localhost/56cecs_${prefix}?useConfigs=maxPerformance&characterEncoding=utf8"
sed -i "/jdbc:mysql:\/\/localhost\/56cecs/c$TEMP_DB" 56cecs/config/local.properties

# temporary change EMS to 8080 - (separate tomcat instance running on jenkins)
# this is because ant inititalize don't start real tomcat with EMS
TEMP_EMS="ems.client.endpoint.uri=http://localhost:8080/entitlements-web/rest/"
sed -i.release "/ems.client.endpoint.uri=http:\/\/localhost:9001/c$TEMP_EMS" 56cecs/config/local.properties

# developer licence
cp -fv /home/jenkins/applications/configs/hybris_license/hybrislicence.jar 56cecs/config/licence/

# copy mysql driver to bin/platform/tomcat/lib for core+ apps like persistentsbg
cp -fv 56cecs/bin/platform/lib/dbdriver/mysql-connector-java-*.jar 56cecs/bin/platform/tomcat/lib/

if [ "$include_tomcat" = "true" ]; then
  cp -fv 56cecs/bin/platform/lib/dbdriver/mysql-connector-java-*.jar 56cecs/coreplus/apache-tomcat/lib
fi