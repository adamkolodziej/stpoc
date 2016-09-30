set BASE_DIR=..\..\..\..
set MVN_REPO_LOCAL=%BASE_DIR%\hybris-dependencies
set DATAHUB_EXT_DIR=%BASE_DIR%\bin\ext-integration\datahub\extensions\sap

set DEP_VERSION=5.6.0.0-RC5

echo "Trying to install jars with version %DEP_VERSION% from %DATAHUB_EXT_DIR%"
mvn install:install-file -DgroupId=com.hybris.datahub -DartifactId=sapcoreconfiguration -Dversion=%DEP_VERSION% -Dpackaging=jar -Dfile=%DATAHUB_EXT_DIR%\sapcoreconfiguration-%DEP_VERSION%.jar -DgeneratePom=true -Dmaven.repo.local=%MVN_REPO_LOCAL%

