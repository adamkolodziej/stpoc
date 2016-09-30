this directory should be populated with symbolic links during init.

Another solution is to replace it with filesystem link to: services-sampledata/video/tricast/
in order to match this property:
customLocalDirectoryMediaStorageStrategy.baseDir=${HYBRIS_BIN_DIR}/../../services-sampledata

or you can just create symbolic links this way:

for /R %G IN (..\..\..\..\..\..\..\services-sampledata\video\tricast\*.mp4) do mklink %~nxG %G
