#!/bin/bash

sampledata_dir=~/services-sampledata/
logger "mirroring sampledata... $sampledata_dir"
mkdir -p $sampledata_dir
cd $sampledata_dir
wget -nH --cut-dirs=1 -m --user=ftpdata --password=hybris4ever ftp://digitalshowcaseci.yse.fra.hybris.com/services-sampledata/
