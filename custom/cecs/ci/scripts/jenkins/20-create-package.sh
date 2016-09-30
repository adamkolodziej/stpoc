# restore local.properties - it had change ems host to separate tomcat 8080 - see above
mv -f 56cecs/config/local.properties.release 56cecs/config/local.properties

mkdir -p instance_dump
cd 56cecs

./coreplus/mongodb-linux/bin/mongod --dbpath ./coreplus/mongo-data --shutdown

mysqldump -u root -proot 56cecs_${prefix} > 56cecs_${prefix}.sql

zip -r -q ../instance_dump/56cecs_${prefix}_data_sql.zip 56cecs_${prefix}.sql data/
zip -r -q ../instance_dump/56cecs_${prefix}_platform.zip bin/ config/ coreplus/ hybris-dependencies/

zip -g ../instance_dump/56cecs_${prefix}_platform.zip cloudpreinstall.sh cloudprebackup.sh autoreset.sh-example update_sampledata.sh
