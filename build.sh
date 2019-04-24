#!/bin/bash

cd accountmanager
mvn clean
source ./env-variable.sh
mvn package
mvn spring-boot:run
cd ..
cd giphermanager
mvn clean
source ./env-variable.sh
mvn package
mvn spring-boot:run

cd ..
