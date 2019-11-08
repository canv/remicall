#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/remicall-1.0-SNAPSHOT.jar \
    canv@192.168.0.3:home/canv/

echo 'Server reboot...'

ssh -i ~/.ssh/id_rsa canv@192.168.0.3 <<EOF

pgrep java | xargs kill -9
nohup java -jar remicall-1.0-SNAPSHOT.jar > log.txt &

EOF

echo 'Complete, bye'
