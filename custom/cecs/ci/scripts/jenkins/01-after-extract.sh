#####
## jenkins working directory: /media/data/sembb/workspace/56cecs-${prefix}
#####

if [ "$clean" = "true" ]; then
# clean up directory
  rm -rf 56cecs
  rm -rf instance_dump

# download here
# 56cecs-bin.zip is a cleaned up version of commerce suite with some adjustments and patches
  unzip -o -q -d 56cecs /home/jenkins/applications/56cecs-${prefix}/56cecs-bin.zip
  unzip -o -q -d 56cecs /home/jenkins/applications/56cecs-${prefix}/hybris-dependencies-56.zip
  unzip -o -q -d 56cecs/bin/custom /home/jenkins/applications/56cecs-${prefix}/56cecs-custom-showcase.zip
  unzip -o -q -d 56cecs /home/jenkins/applications/56cecs-${prefix}/56cecs-coreplus.zip
  cp -fv /home/jenkins/applications/56cecs-${prefix}/datahub/*.jar 56cecs/coreplus/apache-tomcat/datahub/
  cp -fv /home/jenkins/applications/56cecs-${prefix}/ceimock.jar 56cecs/coreplus/

  
# remove tomcat if not required
  if [ "$include_tomcat" = "false" ]; then
    rm -rf 56cecs/coreplus/apache-tomcat
  fi
# remove linux files
#  if [ "$target_os" = "Windows" ]; then
#    rm -rf 56cecs/coreplus/mongodb-linux
#  fi
# remove windows files
#  if [ "$target_os" = "Linux" ]; then
#    rm -rf 56cecs/coreplus/mongodb-windows
#  fi
fi

# copy the extensions to the location where localextensions.xml can find them 
mkdir -p 56cecs/bin/custom/cecs/
cp -fR git-repo-download/* 56cecs/bin/custom/cecs/

if [ "$include_git" = "true" ]; then
  cp -fR git-repo-download/.git/ 56cecs/bin/custom/cecs/.git/
  cp -fR git-repo-download/.gitignore 56cecs/bin/custom/cecs/
fi

cp -f 56cecs/bin/platform/extensions.xml 56cecs/bin/platform/extensions.xml.orig
cp -f git-repo-download/ci/${ciconfig}/localextensions.xml 56cecs/bin/platform/extensions.xml

# link sample data directory to the sibling of (hybris) directory
if [ ! -e services-sampledata ]; then
 ln -s /home/ftpdata/services-sampledata/ .
fi

mysql -u root -proot -e "create database if not exists 56cecs_${prefix}";

cp -fv git-repo-download/ci/scripts/ydc/autoreset.sh-example 56cecs/autoreset.sh-example
cp -fv git-repo-download/ci/scripts/ydc/cloudprebackup-rename-it.sh 56cecs/cloudprebackup.sh
cp -fv git-repo-download/ci/scripts/ydc/cloudpreinstall-rename-it.sh 56cecs/cloudpreinstall.sh
cp -fv git-repo-download/ci/scripts/update_sampledata.sh 56cecs/update_sampledata.sh
cp -fv git-repo-download/ci/scripts/start_mongo.sh 56cecs/coreplus/start_mongo.sh
cp -fv git-repo-download/ci/scripts/start_mongo.bat 56cecs/coreplus/start_mongo.bat
cp -fv git-repo-download/ci/scripts/start_ceimock.sh 56cecs/coreplus/start_ceimock.sh
cp -fv git-repo-download/ci/scripts/start_ceimock.bat 56cecs/coreplus/start_ceimock.bat
cp -fv git-repo-download/ci/scripts/*tomcat.sh 56cecs/coreplus/
cp -fv git-repo-download/ci/scripts/*tomcat.bat 56cecs/coreplus/

cp -fv git-repo-download/ci/scripts/mongo-service/mongod_service_install.bat 56cecs/coreplus/mongod_service_install.bat
cp -fv git-repo-download/ci/scripts/mongo-service/mongod.cfg 56cecs/coreplus/mongo-windows/mongod.cfg


# SBG and EMS configuration
cp -fv git-repo-download/ci/config-${prefix}/ems/* 56cecs/bin/platform/tomcat/lib/


# for separate tomcat
if [ "$include_tomcat" = "true" ]; then
  cp -fv git-repo-download/ci/config-${prefix}/ems/* 56cecs/coreplus/apache-tomcat/lib/
  cp -fv git-repo-download/ci/config-${prefix}/datahub/*.properties 56cecs/coreplus/apache-tomcat/lib/
  cp -fv git-repo-download/ci/config-${prefix}/datahub/cecs-encryption-key.txt 56cecs/coreplus/apache-tomcat/lib/
  cp -fv git-repo-download/ci/config-${prefix}/datahub/context.xml 56cecs/coreplus/apache-tomcat/conf/
fi

chmod a+x 56cecs/*.sh
chmod a+x 56cecs/coreplus/*.sh
chmod a+x 56cecs/coreplus/mongodb-linux/bin/*

cd 56cecs/coreplus

if [ "`pidof mongod`" != "" ]; then 
  killall mongod
fi

./start_mongo.sh
