#!/bin/bash
export name=saml_template
export group=transpetro
export calculatedVersion=1.0
export outerport=9004

#docker stop saml_template
docker rm -f $name
docker rmi  $group/$name:$calculatedVersion
export USER=2
export PASSWD_INTEGRACAO=3
export ref=4

./gradlew build buildDocker
docker run --name $name -d -p $outerport:8999  -e "TZ=America/Sao_Paulo"   $group/$name:$calculatedVersion
docker logs -f $name

